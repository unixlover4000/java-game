public final class Input {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shooting;

    public void set(Direction direction, boolean pressed) {
        switch (direction) {
            case UP -> up = pressed;
            case DOWN -> down = pressed;
            case LEFT -> left = pressed;
            case RIGHT -> right = pressed;
        }
    }

    public boolean up() {
        return up;
    }

    public boolean down() {
        return down;
    }

    public boolean left() {
        return left;
    }

    public boolean right() {
        return right;
    }

    public boolean shooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
