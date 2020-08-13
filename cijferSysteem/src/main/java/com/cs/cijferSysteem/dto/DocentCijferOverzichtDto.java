package com.cs.cijferSysteem.dto;

import java.util.List;

import com.cs.cijferSysteem.domein.LeerlingCijfersVanToetsIds;

public class DocentCijferOverzichtDto {
	List<LeerlingCijfersVanToetsIds> list;

	public List<LeerlingCijfersVanToetsIds> getList() {
		return list;
	}

	public void setList(List<LeerlingCijfersVanToetsIds> list) {
		this.list = list;
	}
}
