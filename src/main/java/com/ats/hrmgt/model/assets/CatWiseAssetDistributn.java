package com.ats.hrmgt.model.assets;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CatWiseAssetDistributn {
@Id

	private int assetCatId;
	private int assetCount;
	private int assetStatus;
	private String catName;
	private String statusText;
	public int getAssetCatId() {
		return assetCatId;
	}
	public void setAssetCatId(int assetCatId) {
		this.assetCatId = assetCatId;
	}
	public int getAssetCount() {
		return assetCount;
	}
	public void setAssetCount(int assetCount) {
		this.assetCount = assetCount;
	}
	public int getAssetStatus() {
		return assetStatus;
	}
	public void setAssetStatus(int assetStatus) {
		this.assetStatus = assetStatus;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	@Override
	public String toString() {
		return "CatWiseAssetDistributn [assetCatId=" + assetCatId + ", assetCount=" + assetCount + ", assetStatus="
				+ assetStatus + ", catName=" + catName + ", statusText=" + statusText + "]";
	}
	
}
