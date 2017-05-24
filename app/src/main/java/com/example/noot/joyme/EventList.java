package com.example.noot.joyme;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

public class EventList extends BaseCustomViewGroup {

    TextView titleView, placeView, timeView, limitNumberView;

    public EventList(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public EventList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public EventList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public EventList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_event, this);

    }

    private void initInstances() {
        // findViewById here
        titleView = (TextView) findViewById(R.id.titleView);
        placeView = (TextView) findViewById(R.id.placeView);
        timeView = (TextView) findViewById(R.id.timeView);
        limitNumberView = (TextView) findViewById(R.id.limitMemberView);
    }

    public void setTitleView(String text){
        titleView.setText(text);
    }
    public void setPlaceView(String text){
        placeView.setText(text);
    }
    public void setTimeView(String text){
        timeView.setText(text);
    }
    public void setLimitNumberView(String text){
        limitNumberView.setText(text);
    }


    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width /2;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        // child
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // self
        setMeasuredDimension(width,height);
    }
}
