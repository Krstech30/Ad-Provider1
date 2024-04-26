package com.java.adProvider.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.adProvider.model.EntryDetails;
import com.java.adProvider.model.ImageModel;
import com.java.adProvider.model.Plan;
import com.java.adProvider.model.Product;
import com.java.adProvider.model.ReferenceDataCategory;
import com.java.adProvider.model.VideoDetails;
import com.java.adProvider.repo.EntryDetailsRepository;
import com.java.adProvider.repo.ImageModelRepository;
import com.java.adProvider.repo.VideoDetailsRepository;
import com.java.adProvider.response.ResponseHandler;
import com.java.adProvider.service.EntryDetailsService;
import com.java.adProvider.service.PlanService;
import com.java.adProvider.service.ProductService;
import com.java.adProvider.service.ReferenceDataCategoryService;
import com.java.adProvider.service.ReferenceDataService;
import com.java.adProvider.service.VideoDetailsService;

@RestController
@RequestMapping("/entrydetails")
@CrossOrigin("*")
public class EntryDetailsController {

	@Autowired
	private EntryDetailsService entryDetailsService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private PlanService planService;
	@Autowired
	private ReferenceDataService referenceDataService;
	@Autowired
	private ReferenceDataCategoryService referenceDataCategoryService;
	@Autowired
	private EntryDetailsRepository entryDetailsRepository;

	@Autowired
	private VideoDetailsService videoService;

	@Autowired
	private ImageModelRepository imageModelRepository;

	@Autowired
	private VideoDetailsRepository videoDetailsRepository;

	@Value("${project.videos}")
	private String path;

//	@Value("${server.compression.mime-types}")
//    private List<String> contentVideos;

	@GetMapping("/list")
	public ResponseEntity<?> getEntryDetails() {
		List<EntryDetails> ed = entryDetailsService.findAllWithProductAndPlan();
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of EntryDeatils", HttpStatus.OK.value(), ed,
				HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> saveEntryDetails(@RequestPart("entryDetails") String entryDetails,
			@RequestPart(value = "imageFile", required = false) MultipartFile[] imageFile,
			@RequestPart(value = "videoFile", required = false) MultipartFile[] videoFile) {
		EntryDetails entryDetails1 = new EntryDetails();
		try {
			Plan plan = new Plan();
			Set<ImageModel> images = null;
			Set<VideoDetails> videos = null;
			if (imageFile != null) {
				images = uploadImage(imageFile);
			}

			if (videoFile != null) {
				videos = uploadVideo(videoFile);
			}

			EntryDetails newentryDetails = mapper.readValue(entryDetails, EntryDetails.class);
			entryDetails1.setVerification_status(newentryDetails.isVerification_status());
			entryDetails1.setCampaign_name(newentryDetails.getCampaign_name());
			entryDetails1.setManufacturer_brand(newentryDetails.getManufacturer_brand());
			entryDetails1.setStartdate(newentryDetails.getStartdate());
			entryDetails1.setEnddate(newentryDetails.getEnddate());
			entryDetails1.setCreated_by(newentryDetails.getCreated_by());
			entryDetails1.setCreated_date(newentryDetails.getCreated_date());
			entryDetails1.setModified_by(newentryDetails.getModified_by());
			entryDetails1.setModified_date(newentryDetails.getModified_date());
			entryDetails1.set_system_modified(newentryDetails.is_system_modified());
			entryDetails1.setSystem_modified_date(newentryDetails.getSystem_modified_date());
			entryDetails1.setRow_version(newentryDetails.getRow_version());
			Product product = createOrUpdateProduct(newentryDetails.getProduct());

			plan = newentryDetails.getPlan();
			if (plan.getPlan_id() == null) {
				plan.setPlan_description(newentryDetails.getPlan().getPlan_description());
				plan.setDuration(newentryDetails.getPlan().getDuration());
				plan.setLength_duration(newentryDetails.getPlan().getLength_duration());
				plan.setNo_viewer(newentryDetails.getPlan().getNo_viewer());
				plan.set_system_modified(newentryDetails.is_system_modified());
				planService.createPlan(plan);

			}

			entryDetails1.setPlan(plan);

			entryDetails1.setImages(images);
			entryDetails1.setVideo(videos);
			entryDetails1.setProduct(product);

			EntryDetails createEntry = entryDetailsService.saveEntryDetails(entryDetails1);
			return ResponseEntity.ok(createEntry);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse entry details", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to save entry details.", e);
		}
	}

	@PostMapping("/update/{entry_id}/{imageId}")
	public ResponseEntity<?> update(@PathVariable Long entry_id, @PathVariable Long imageId,
			/*@PathVariable Long video_id,*/ @RequestPart("entryDetails") String entryDetails,
			@RequestPart(value = "imageFile", required = false) MultipartFile[] imageFile,
			@RequestParam(value = "videoFile", required = false) MultipartFile[] videoFile) {
		try {
			Set<VideoDetails> videos = null;
			Set<ImageModel> images = null;
			EntryDetails entryDetails1 = entryDetailsRepository.findById(entry_id).orElseThrow();
			if (entryDetails1 == null) {
				return ResponseEntity.notFound().build();
			}
			if (imageFile != null && imageFile.length > 0) {
				images=updateImage(imageFile,imageId);
			}
            if(videoFile!=null && videoFile.length>0) {
//            	images=updateVideo(videoFile,video_id);
            }
			Product product = new Product();
			Plan plan = new Plan();
			EntryDetails entryDetails2 = mapper.readValue(entryDetails, EntryDetails.class);
			entryDetails1.setVerification_status(entryDetails2.isVerification_status());
			entryDetails1.setCampaign_name(entryDetails2.getCampaign_name());
			entryDetails1.setManufacturer_brand(entryDetails2.getManufacturer_brand());
			entryDetails1.setStartdate(entryDetails2.getStartdate());
			entryDetails1.setEnddate(entryDetails2.getEnddate());
			entryDetails1.setCreated_by(entryDetails2.getCreated_by());
			entryDetails1.setCreated_date(entryDetails2.getCreated_date());
			entryDetails1.setModified_by(entryDetails2.getModified_by());
			entryDetails1.setModified_date(entryDetails2.getModified_date());
			entryDetails1.set_system_modified(entryDetails2.is_system_modified());
			entryDetails1.setSystem_modified_date(entryDetails2.getSystem_modified_date());
			entryDetails1.setRow_version(entryDetails2.getRow_version());
			// product
			product = entryDetails1.getProduct();
			if (product.getProduct_id() != null) {
				product.setName(entryDetails2.getProduct().getName());
				product.setCompany_name(entryDetails2.getProduct().getCompany_name());
				product.setDescription(entryDetails2.getProduct().getDescription());
				product.setPrice(entryDetails2.getProduct().getPrice());
				product.setStock_quantity(entryDetails2.getProduct().getStock_quantity());
				ReferenceDataCategory referenceDataCat = new ReferenceDataCategory();
				referenceDataCat = entryDetails1.getProduct().getReferenceDataCategory();
				if (referenceDataCat.getCategory_id() != null) {
					referenceDataCat.setCat_description(
							entryDetails2.getProduct().getReferenceDataCategory().getCat_description());
					referenceDataCat.setShort_description(
							entryDetails2.getProduct().getReferenceDataCategory().getShort_description());
					referenceDataCat = referenceDataCategoryService.updateReferenceDataCategory1(referenceDataCat);
				}
				product = productService.updateProducts(product);
			}

			entryDetails1.setProduct(product);
			// plan
			plan = entryDetails1.getPlan();
			if (plan.getPlan_id() != null) {
				plan.setPlan_description(entryDetails2.getPlan().getPlan_description());
				plan.setDuration(entryDetails2.getPlan().getDuration());
				plan.setLength_duration(entryDetails2.getPlan().getLength_duration());
				plan.setNo_viewer(entryDetails2.getPlan().getNo_viewer());
				plan.set_system_modified(entryDetails2.is_system_modified());
				planService.updatePlan1(plan);
			} else {
				plan = entryDetails2.getPlan();
			}

			entryDetails1.setPlan(plan);
			entryDetails1.setImages(images);

			EntryDetails updateEntry = entryDetailsService.update(entry_id, entryDetails1, videoFile);

			if (updateEntry != null) {
				return ResponseEntity.ok(updateEntry);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("failed to update EntryDetails: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("failed to update EntryDetails: " + e.getMessage());
		}

	}

	@DeleteMapping("/delete/{entry_id}")
	public ResponseEntity<?> deleteEntryDetails(@PathVariable Long entry_id) {
		entryDetailsService.deleteEntryById(entry_id);

		return ResponseEntity.ok().build();
	}

	// method for image
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFile) throws IOException {
		Set<ImageModel> images = new HashSet<>();
		if (multipartFile != null) {
			for (MultipartFile file : multipartFile) {

				ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getBytes(),
						file.getContentType());
				images.add(imageModel);
			}
		}
		return images;
	}

	public Set<ImageModel> updateImage(MultipartFile[] multipartFile, Long imageId) throws IOException {
		Set<ImageModel> images = new HashSet<>();
		ImageModel imageModel = imageModelRepository.findById(imageId).orElseThrow();
		if (multipartFile != null) {
			for (MultipartFile file : multipartFile) {
				String originalFilename = file.getOriginalFilename();
				byte[] fileBytes = file.getBytes();
				String contentType = file.getContentType();
				imageModel.setName(originalFilename);
				imageModel.setContentType(contentType);
				imageModel.setPicBytes(fileBytes);
				images.add(imageModel);
				imageModel = imageModelRepository.save(imageModel);
				images.add(imageModel);

			}
		}
		return images;

	}

	// method end for images

	// function for video
	public Set<VideoDetails> uploadVideo(MultipartFile[] file) throws IOException {
		Set<VideoDetails> videoDetailsSet = new HashSet<>();
		if (file != null) {
			for (MultipartFile files : file) {
				String contentType = files.getContentType();
				byte[] videoByte = files.getBytes();
				VideoDetails video1 = new VideoDetails();
				video1 = videoService.createFile(files);
				video1.setContentType(contentType);
				video1.setVideoBytes(videoByte);
				videoDetailsSet.add(video1);

			}

		}
		return videoDetailsSet;
	}
	public Set<VideoDetails> updateVideo(MultipartFile[] file,Long video_Id) throws IOException {
		Set<VideoDetails> videoDetailsSet = new HashSet<>();
		VideoDetails videoDetails=videoDetailsRepository.findById(video_Id).orElseThrow();

		if (file != null) {
			for (MultipartFile files : file) {
				String contentType = files.getContentType();
				byte[] videoByte = files.getBytes();
				VideoDetails video1 = new VideoDetails();
				video1 = videoService.updateFile(files);
				video1.setContentType(contentType);
				video1.setVideoBytes(videoByte);
				videoDetailsSet.add(video1);

			}

		}
		return videoDetailsSet;
	}

	// method for product
	private Product createOrUpdateProduct(Product product) {
		ReferenceDataCategory category = new ReferenceDataCategory();
		if (product.getProduct_id() == null) {

			if (category.getCategory_id() == null) {
				category.setCat_description(product.getReferenceDataCategory().getCat_description());
				category.setShort_description(product.getReferenceDataCategory().getShort_description());
				category = referenceDataCategoryService.createReferenceDataCategory(category);
			}
			product.setReferenceDataCategory(category);
			Product prod = productService.createProduct(product);
			return prod;

		}
		return product;
	}

}
