package com.example.cs2340cteam50game.model;

public class Rectangle {
    private float left;
    private float top;
    private float right;
    private float bottom;

    public Rectangle(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }


    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public void updatePosition(double stepX, double stepY) {
        left += stepX;
        right += stepX;
        top += stepY;
        bottom += stepY;
    }

    //

    public boolean intersectsWall(Rectangle other, int direction) {
        if (this.right < other.left || other.right < this.left) {
            return false;
        }

        if (this.bottom < other.top || other.bottom < this.top) {
            return false;
        }

        if (direction == 0 && this.left < other.right && this.right > other.left) {
            if (this.left < other.left) {
                this.left = other.left - (this.right - this.left) - 1;
            }

            if (this.right > other.right) {
                this.left = other.right + 1;
            }
        }

        if (direction == 1 && this.top < other.bottom && this.bottom > other.top) {
            if (this.top < other.top) {
                this.top = other.top - (this.bottom - this.top) - 1;
            }
            if (this.bottom > other.bottom) {
                this.top = other.bottom + 1;
            }
        }

        return true;
    }

    public boolean intersectsEnemy(Rectangle other, int direction) {
        if (this.right < other.left || other.right < this.left) {
            return false;
        }

        if (this.bottom < other.top || other.bottom < this.top) {
            return false;
        }

        if (direction == 0 && this.left < other.right && this.right > other.left) {
            if (this.left < other.left) {
                this.left = other.left - (this.right - this.left) - 10;
            }

            if (this.right > other.right) {
                this.left = other.right + 10;
            }
        }

        if (direction == 1 && this.top < other.bottom && this.bottom > other.top) {
            if (this.top < other.top) {
                this.top = other.top - (this.bottom - this.top) - 10;
            }
            if (this.bottom > other.bottom) {
                this.top = other.bottom + 10;
            }
        }

        return true;
    }

    public boolean intersects(Rectangle other) {
        if (this.right < other.left || other.right < this.left) {
            return false;
        }

        if (this.bottom < other.top || other.bottom < this.top) {
            return false;

        }

        return true;
    }
}


