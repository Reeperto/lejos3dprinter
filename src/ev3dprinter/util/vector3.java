package ev3dprinter.util;

public class vector3 {

    public float x;
    public float y;
    public float z;

    public vector3(float x, float y, float z) {
        this.set(x,y,z);
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float magnitude2(vector3 v) {
        return v.x*v.x + v.y + v.y + v.z + v.z;
    }


}
