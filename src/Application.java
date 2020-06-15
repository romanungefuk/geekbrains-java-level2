public class Application {

    public static void main(String[] args) {

        //Создаем массив с участниками типа Runnable(необходимо для обобщения, чтоб можно было упаковать в один массив)
        Runnable[] participants = new Runnable[6];
        participants[0] = new Human("Артур", 100, 2);
        participants[1] = new Human("Леонардо", 5000, 1);
        participants[2] = new Cat("Мурзик", 10, 5);
        participants[3] = new Cat("Барсик", 1, 1);
        participants[4] = new Android("R2-D2", 10000, 5);
        participants[5] = new Android("С-3PO", 1500, 0);

        //Создаем массив с препятствиями для частников
        Obstacle[] obstacles = new Obstacle[4];
        obstacles[0] = new RunningWay(90);
        obstacles[1] = new RunningWay(500);
        obstacles[2] = new Wall(1);
        obstacles[3] = new Wall(5);

        //Заставляем всех участников пройти этот набор препятствий
        for (Runnable r : participants) {
            for (Obstacle o : obstacles) {
                boolean isPassingObstacle = true;
                if (r instanceof Human) {
                    isPassingObstacle = ((Human) r).passingObstacle(o);
                } else if (r instanceof Cat) {
                    isPassingObstacle = ((Cat) r).passingObstacle(o);
                } else {
                    isPassingObstacle = ((Android) r).passingObstacle(o);
                }
                if (!isPassingObstacle) {
                    break;
                }
            }
        }

    }
}
