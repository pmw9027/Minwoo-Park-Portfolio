package com.rival.hs.user.domain;

/**
 * Created by Minwoo on 2017. 4. 26..
 */
public class BilliardPlayerDo extends PlayerDo{

    private int carrom_three_ball_score;
    private int carrom_four_ball_score;
    private int pocketball_score;

    public BilliardPlayerDo(int carrom_three_ball_score, int carrom_four_ball_score, int pocketball_score) {
        this.carrom_three_ball_score = carrom_three_ball_score;
        this.carrom_four_ball_score = carrom_four_ball_score;
        this.pocketball_score = pocketball_score;
    }

    public int getCarrom_three_ball_score() {
        return carrom_three_ball_score;
    }

    public void setCarrom_three_ball_score(int carrom_three_ball_score) {
        this.carrom_three_ball_score = carrom_three_ball_score;
    }

    public int getCarrom_four_ball_score() {
        return carrom_four_ball_score;
    }

    public void setCarrom_four_ball_score(int carrom_four_ball_score) {
        this.carrom_four_ball_score = carrom_four_ball_score;
    }

    public int getPocketball_score() {
        return pocketball_score;
    }

    public void setPocketball_score(int pocketball_score) {
        this.pocketball_score = pocketball_score;
    }
}
