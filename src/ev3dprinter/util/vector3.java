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

    public float magnitude3() {
        return this.x*this.x + this.y + this.y + this.z + this.z;
    }

    public float magnitude2() {
        return this.x*this.x + this.y + this.y;
    }

    public void invert() {
        
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        
    }

    public vector3 sub(vector3 w) {
        return new vector3(this.x - w.x, this.y - w.y, this.z - w.z);
    }

}
