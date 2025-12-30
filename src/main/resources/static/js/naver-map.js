var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.3207844, 127.12789865),
    zoom: 16
});

const buildingDiv = new Map();
const divMarker = new Map();

buildings.forEach(building => {
    const constentString = `<div class="marker-div">
    <a href="/client/classroom/${building.engShort}">${building.korFull}</a>
</div>`;

    buildingDiv.set(building.engShort, constentString);

    const marker = new naver.maps.Marker({
        map: map,
        position: new naver.maps.LatLng(building.latitude, building.longitude)
        
    })
    divMarker.set(building.engShort, marker);

    const infowindow = new naver.maps.InfoWindow({
        content: buildingDiv.get(building.engShort)
    })

    funcMarker(infowindow, building.engShort);
});

function funcMarker(infowindow, name) {
    naver.maps.Event.addListener(divMarker.get(name), "click", function(e) {
        if (infowindow.getMap()) {
            infowindow.close();
        } else {
            infowindow.open(map, divMarker.get(name));
        }
    })
}