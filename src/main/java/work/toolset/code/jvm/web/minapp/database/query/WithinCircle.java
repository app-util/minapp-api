package work.toolset.code.jvm.web.minapp.database.query;


import work.toolset.code.jvm.web.minapp.database.GeoPoint;

public class WithinCircle {

    private GeoPoint center;
    private float radius;

    public WithinCircle(GeoPoint center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public GeoPoint getCenter() {
        return center;
    }

    public void setCenter(GeoPoint center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
