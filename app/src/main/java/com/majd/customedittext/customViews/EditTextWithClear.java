package com.majd.customedittext.customViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.majd.customedittext.R;


public class EditTextWithClear extends AppCompatEditText {
    //TODO: 2- Define a member variable for the drawable (the X button image).
    private Drawable clearButtonImage;


    public EditTextWithClear(Context context) {
        super(context);
        //TODO: 4- Add the init() method call to each constructor.
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //TODO: 3- Create a private method called init().
    // with no parameters, that initializes the member variable to the drawable resource ic_clear_opaque_24dp.
    //and to set up the paint and the attributes
    private void init() {
        clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_close, null);
    //TODO: 5- add a  addTextChangedListener() inside the init() method.
    addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            showClearButton();

        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    });
        //TODO: 6- set OnTouchListener
    setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(getCompoundDrawablesRelative()[2]!=null){
                float clearButtonStart; // Used for LTR languages
                float clearButtonEnd;  // Used for RTL languages
                boolean isClearButtonClicked = false;

                // Detect the touch in RTL or LTR layout direction.
                if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                    // If RTL, get the end of the button on the left side.
                    clearButtonEnd = clearButtonImage
                            .getIntrinsicWidth() + getPaddingStart();
                    // If the touch occurred before the end of the button,
                    // set isClearButtonClicked to true.
                    if (motionEvent.getX() < clearButtonEnd) {
                        isClearButtonClicked = true;
                    }
                } else {
                    // Layout is LTR.
                    // Get the start of the button on the right side.
                    clearButtonStart = (getWidth() - getPaddingEnd()
                            - clearButtonImage.getIntrinsicWidth());
                    // If the touch occurred after the start of the button,
                    // set isClearButtonClicked to true.
                    if (motionEvent.getX() > clearButtonStart) {
                        isClearButtonClicked = true;
                    }
                }

                if (isClearButtonClicked) {
                    // Check for ACTION_DOWN (always occurs before ACTION_UP).
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        // Switch to the black version of clear button.
                        clearButtonImage =
                                ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.ic_clear_close, null);
                        showClearButton();
                    }
                    // Check for ACTION_UP.
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        // Switch to the opaque version of clear button.
                        clearButtonImage =
                                ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.ic_opaque_close, null);
                        // Clear the text and hide the clear button.
                        getText().clear();
                        hideClearButton();
                        return true;
                    }
                } else {
                    return false;
                }

            }

            return false;
        }
    });

    }

    //TODO: 4- Create a private method showClearButton()
    private void showClearButton() {

        setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                clearButtonImage,
                null);

    }

    //TODO: 5- Create a private method hideClearButton()
    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);

    }
}
