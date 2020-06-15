public class RunningWay extends Obstacle {

    private int length; // Дистанция беговой дорожки

    public RunningWay(int length) {
        this.length = length;
    }

    public int getDistance() {
        return length;
    }

    public void setDistance(int length) {
        this.length = length;
    }
}
