package com.julie.tictactoegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JULIE on 4/17/2018.
 */

public class BoardView extends View {

    private static int LINE_THICK = 3;
    private static int EL_STROKE_WIDTH = 15;
    private int width, height, eltW, eltH;
    private static int EL_MARGIN = 20;
    private Paint gridPaint, oPaint, xPaint;
    private Board board;
    private PlayerAgainstCom activity;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.BLACK);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(EL_STROKE_WIDTH);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);
    }
    /*
    specifying the activity
     */

    public void setMainActivity(PlayerAgainstCom a) {
        activity = a;
    }

    /*
     specifying the board to be used
     */
    public void setBoard(Board g) {
        board = g;
    }

    /*
  this method calculates the dimensions of the cell
   */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        eltW = (width - LINE_THICK) / 3;
        eltH = (height - LINE_THICK) / 3;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!board.isEnded() && event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / eltW);
            int y = (int) (event.getY() / eltH);
            char win = board.play(x, y);
            invalidate();

            if (win != ' ') {
                activity.gameEnded(win);
            } else {
                win = board.computer();
                invalidate();

                if (win != ' ') {
                    activity.gameEnded(win);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /*
    this method draws the board
     */
    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawElt(canvas, board.getElt(i, j), i, j);
            }
        }
    }

    /*
    this method draws the grid
     */
    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // vertical lines
            float left = eltW * (i + 1);
            float right = left + LINE_THICK;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = eltH * (i + 1);
            float bottom2 = top2 + LINE_THICK;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    /*
    this method draws the elements of the board ; the "X" and "O"
     */
    private void drawElt(Canvas canvas, char s, int x, int y) {
        if (s == 'O') {
            float sx = (eltW * x) + eltW / 2;
            float sy = (eltH * y) + eltH / 2;

            canvas.drawCircle(sx, sy, Math.min(eltW, eltH) / 2 - EL_MARGIN * 2, oPaint);

        } else if (s == 'X') {
            float startX = (eltW * x) + EL_MARGIN;
            float startY = (eltH * y) + EL_MARGIN;
            float endX = startX + eltW - EL_MARGIN * 2;
            float endY = startY + eltH - EL_MARGIN;

            canvas.drawLine(startX, startY, endX, endY, xPaint);

            float startX2 = (eltW * (x + 1)) - EL_MARGIN;
            float startY2 = (eltH * y) + EL_MARGIN;
            float endX2 = startX2 - eltW + EL_MARGIN * 2;
            float endY2 = startY2 + eltH - EL_MARGIN;

            canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
        }
    }

}