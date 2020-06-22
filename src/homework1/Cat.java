public class Cat implements Runnable, Jumpable {
    private String name; // Прозвище кота
    private int maxRunDistance; // максимальная беговая дистанция кота
    private int maxJumpHigh; // максимальная высота прыжка в высоту кота

    /**
     * Конструктор объекта кота
     * @param name Прозвище кота
     * @param maxRunDistance максимальная беговая дистанция кота
     * @param maxJumpHigh максимальная высота прыжка в высоту кота
     */
    public Cat(String name, int maxRunDistance, int maxJumpHigh) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHigh = maxJumpHigh;
    }

    @Override
    public void run() {
        System.out.println("Кот " + name + " бежит.");
    }

    @Override
    public void jump() {
        System.out.println("Кот " + name + " прыгает.");
    }

    /**
     * Метод преодоления котом препятствия
     *
     * @param obstacle RunningWay или Wall
     */
    public boolean passingObstacle(Obstacle obstacle) {
        boolean result = true;
        if (obstacle instanceof RunningWay) {
            int length = ((RunningWay) obstacle).getDistance();
            if (this.getMaxRunDistance() >= length) {
                System.out.println("Кот " + this.getName() + " успешно пробежал " + length + " метров.");
            } else {
                System.out.println("Кот " + this.getName()
                        + " не смог преодолеть дистанцию в " + length + " метров.");
                result = false;
            }
        } else if (obstacle instanceof Wall) {
            int height = ((Wall) obstacle).getHeight();
            if (this.getMaxJumpHigh() >= height) {
                System.out.println("Кот " + this.getName() + " успешно перепрыгнул стену в " + height + " метров.");
            } else {
                System.out.println("Кот " + this.getName()
                        + " не смог преодолеть стену высотой в " + height + " метров.");
                result = false;
            }
        } else {
            System.out.println("Передавайте в метод только объект RunningWay или Wall.");
            result = false;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxJumpHigh() {
        return maxJumpHigh;
    }

    public void setMaxJumpHigh(int maxJumpHigh) {
        this.maxJumpHigh = maxJumpHigh;
    }
}
