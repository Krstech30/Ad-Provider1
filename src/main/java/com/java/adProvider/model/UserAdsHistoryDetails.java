package com.java.adProvider.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user_ads_history_details")
public class UserAdsHistoryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ads_history_id;
	private Long ads_category_id = 20L;
	private Long ads_id = 20L;
	private String active_from_date;
	private boolean total_ads_viewed;
	private boolean total_redeem_point;
	private double total_amount_transferred_to_bank;
	private double amount_remaining;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private User user;

}
