package src.models;

public class Pacman extends Thread {
    int pointsEaten = 0;

    public enum directions {
        Up,
        Down,
        Left,
        Right
    }

    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    World world1;

    public Pacman(World w) {
        world1 = w;
    }

    public directions getDirection() {
        return direction;
    }

    public void run(){
        setPriority(1);
        while (true){
            try{
                sleep(450);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.move();
            noPointsLeft();
        }
    }

    public void move(){
        switch (directionNew){
            case Up:
                if(world1.y-1 >= 0 && world1.xyWorld[world1.y-1%world1.xyWorld.length][world1.x]){
                    world1.y--;
                    increasePoints();
                    world1.itemXyWorld[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(world1.y+1 < world1.xyWorld.length && world1.xyWorld[world1.y+1%world1.xyWorld.length][world1.x]){
                    world1.y++;
                    increasePoints();
                    world1.itemXyWorld[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                if(world1.x-1 >= 0 && world1.xyWorld[world1.y][world1.x-1%world1.xyWorld[0].length]){
                    world1.x--;
                    increasePoints();
                    world1.itemXyWorld[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
                if(world1.x+1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x+1%world1.xyWorld[0].length]){
                    world1.x++;
                    increasePoints();
                    world1.itemXyWorld[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            default:
                break;
        }
    }

    public void increasePoints(){
        if (world1.itemXyWorld[world1.y][world1.x]){
            pointsEaten++;
            System.out.println("Pointseaten: " + pointsEaten);
        }
    }


    public void impossibleDirection(){
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (world1.y - 1 >= 0 && world1.xyWorld[world1.y - 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case Down:
                if (world1.y + 1 < world1.xyWorld.length && world1.xyWorld[world1.y + 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case Left:
                if (world1.x - 1 >= 0 && world1.xyWorld[world1.y][world1.x - 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
            case Right:
                if (world1.x + 1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x + 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    public void noPointsLeft(){//Noah deine Methode wenn alle Punkte gegessen sind
        if (pointsEaten == world1.getCounter()){
            System.out.println("Alle Punkte aufgegessen");
        }
    }

    //UP: Y wird kleiner
    public void moveUp(){
        directionNew = directions.Up;
    }

    //DOWN: Y wird größer
    public void moveDown(){
        directionNew = directions.Down;
    }

    //LEFT: X wird kleiner
    public void moveLeft(){
        directionNew = directions.Left;
    }

    //RIGHT: X wird größer
    public void moveRight(){
        directionNew = directions.Right;
    }
}