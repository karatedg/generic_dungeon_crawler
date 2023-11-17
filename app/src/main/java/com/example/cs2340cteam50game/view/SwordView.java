package com.example.cs2340cteam50game.view;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.model.Sword;

public class SwordView extends View {
    private Sword sword;
    private Bitmap swordSprite;
    private float width;
    private float height;

    public float getSpriteWidth() {
        return width;
    }
    public float getSpriteHeight() {
        return height;
    }
    private volatile float x;
    private volatile float y;

    public SwordView(Context context, Sword sword) {
        super(context);

        this.sword = sword;
        int spriteID = R.drawable.sword;
        Drawable sprite = getResources().getDrawable(spriteID);
        sword.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) sword.getSprite()).getBitmap();
        this.x = (float) PlayerClass.getPlayer().getxPos();
        this.y = (float) PlayerClass.getPlayer().getyPos() + 50;
        swordSprite = Bitmap.createScaledBitmap(temp, (int) (.25 * temp.getWidth()),
                (int) (.25 * temp.getHeight()), true);
        width = swordSprite.getWidth();
        height = swordSprite.getHeight();
        sword.setSpriteWidth(width);
        sword.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(swordSprite, x, y, null);
    }
}
