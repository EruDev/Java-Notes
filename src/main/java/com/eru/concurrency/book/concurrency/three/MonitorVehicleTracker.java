package com.eru.concurrency.book.concurrency.three;

import com.eru.concurrency.book.concurrency.annotations.GuardedBy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eru on 2020/7/6.
 */
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> m){
        this.locations = deepCopy(m);
    }

    public synchronized Map<String, MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint point = locations.get(id);
        return point == null? null: new MutablePoint(point);
    }

    public synchronized void setLocations(String id, int x, int y){
        MutablePoint point = locations.get(id);
        if (point == null){
            throw new IllegalArgumentException("No such id" + id);
        }
        point.x = x;
        point.y = y;
    }

    public static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id: m.keySet()){
            result.put(id, m.get(id));
        }
        return Collections.unmodifiableMap(result);
    }
}
