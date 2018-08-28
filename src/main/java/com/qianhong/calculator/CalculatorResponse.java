package com.qianhong.calculator;

import java.util.Date;

public class CalculatorResponse {

    int _x;
    int _y;
    int _result;
    String _time;

    public CalculatorResponse(int x, int y, int result) {
        _x = x;
        _y = y;
        _result = result;
        _time = new Date().toString();
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getResult() {
        return _result;
    }

    public String getTime() {
        return _time;
    }
}
