package com.kvvssut.mapper;

import com.kvvssut.dto.RestDTO;
import com.kvvssut.model.RestData;

public final class DataToDTOMapper {

	public static final RestDTO restDataToRestDTOMapper(RestData data) {
		RestDTO dto = new RestDTO();
		dto.setId(data.getId());
		dto.setUserName(data.getUserName());
		dto.setTotalBalance(data.getTotalBalance());
		dto.setCreatedAt(data.getCreatedAt());
		return dto;
	}
}
