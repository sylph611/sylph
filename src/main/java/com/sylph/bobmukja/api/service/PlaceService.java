package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.repository.PlaceRepository;
import com.sylph.bobmukja.api.web.dto.LatLng;
import com.sylph.bobmukja.api.web.dto.MapRequest;
import com.sylph.bobmukja.api.web.dto.PlaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    // 장소 단건 조회
    public Place get(long id) {
        return placeRepository.findById(id).orElse(null);
    }

    // 장소 다건 조회
    public List<Place> getList() {
        return placeRepository.findAll();
    }

    // 좌표 기준 장소 조회
    public String search(MapRequest mapRequest) {
        LatLng center = mapRequest.getCenter();
        LatLng topRight = mapRequest.getTopRight();
        LatLng bottomLeft = mapRequest.getBottomLeft();

        if(topRight.isEmpty() || bottomLeft.isEmpty()) {

        } else {

        }

        return "";
    }

    // 장소 다건 저장
    public boolean save(List<PlaceRequest> placeRequestList) {
        placeRequestList.stream().map(request -> placeRepository.save(request.toEntity()));
        return true;
    }

    // 장소 다건 삭제
    public boolean delete(List<Long> placeIdList) {
        List<Place> placeList = placeIdList.stream().map(p -> placeRepository.findByIdAndDeletedIsFalse(p).orElse(null)).toList();
        placeList.stream().map(Place::deleted);
        return true;
    }

}
