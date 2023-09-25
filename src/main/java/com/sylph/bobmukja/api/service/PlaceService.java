package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.repository.PlaceRepository;
import com.sylph.bobmukja.api.web.dto.LatLng;
import com.sylph.bobmukja.api.web.dto.MapRequest;
import com.sylph.bobmukja.api.web.dto.PlaceRequest;
import com.sylph.bobmukja.api.web.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<PlaceResponse> search(MapRequest mapRequest, int page) {
        //LatLng center = mapRequest.toCenterLatLng();
        LatLng topRight = mapRequest.toTopRightLatLng();
        LatLng bottomLeft = mapRequest.toBottomLeftLatLng();


        Page<Place> placeList = null;
        if(!topRight.isEmpty() && !bottomLeft.isEmpty()) {
            placeRepository.findByIdAndDeletedIsFalse(30L);
            placeList = placeRepository.findPlacesByLatitudeBetweenAndLongitudeBetween(bottomLeft.getLatitude(),topRight.getLatitude(),
                    bottomLeft.getLongitude(), topRight.getLongitude(), PageRequest.of(page,30));
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
