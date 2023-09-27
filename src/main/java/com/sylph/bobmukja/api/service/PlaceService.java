package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.repository.PlaceRepository;
import com.sylph.bobmukja.api.domain.specification.PlaceSpecification;
import com.sylph.bobmukja.api.web.dto.external.MapRequest;
import com.sylph.bobmukja.api.web.dto.external.PlaceRequest;
import com.sylph.bobmukja.api.web.dto.external.PlaceResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceSearchRequest;
import com.sylph.bobmukja.api.web.dto.internal.LatLng;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Place> getList(PlaceSearchRequest placeSearchRequest, int page) {
        return placeRepository.findAll(PlaceSpecification.search(placeSearchRequest), PageRequest.of(page,30));
    }

    // 현 지도 기준 장소 조회 ( 노출 지도 내 재조회 )
    public Page<PlaceResponse> searchInCurrentMap(MapRequest mapRequest, int page) {
        // 우상단 좌표 추출
        LatLng topRight = mapRequest.toTopRightLatLng();
        // 좌하단 좌표 추출
        LatLng bottomLeft = mapRequest.toBottomLeftLatLng();

        Page<Place> placeList = null;
        if(!topRight.isEmpty() && !bottomLeft.isEmpty()) {
            placeList = placeRepository.findPlacesByLatitudeBetweenAndLongitudeBetween(bottomLeft.getLatitude(),topRight.getLatitude(),
                    bottomLeft.getLongitude(), topRight.getLongitude(), PageRequest.of(page,30));
        }

        return PlaceResponse.of(placeList);
    }

    // 센터 좌표 기준 조회
    public Page<PlaceResponse> search(MapRequest mapRequest, int page) {
        LatLng center = mapRequest.toCenterLatLng();

        Page<Place> placeList = null;
        if(!center.isEmpty()) {
            placeList = placeRepository.findPlacesByCenter(center.getLatitude(), center.getLongitude(), PageRequest.of(page,30));
        }

        return PlaceResponse.of(placeList);
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
