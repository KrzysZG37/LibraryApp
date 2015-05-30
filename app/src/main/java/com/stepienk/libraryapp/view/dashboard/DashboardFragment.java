package com.stepienk.libraryapp.view.dashboard;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.stepienk.libraryapp.view.fragments_decorator.FragmentDecorator;
import com.stepienk.libraryapp.view.fragments_decorator.FragmentInterface;

import info.androidhive.LibraryApp.R;

/**
 * Created by stepienk on 2015-04-15.
 */
public class DashboardFragment extends FragmentDecorator {
    private CardView mCardView1;
    private CardView mCardView2;
    private CardView mCardView3;
    private View header1 = null;
    private View header2 = null;
    private View header3 = null;
    private View cont1;
    private View cont2;
    private View cont3;

    private WidgetInterface widgetListener;

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public DashboardFragment() {
    }

    public DashboardFragment(FragmentInterface dashboardFragment) {
        fi = dashboardFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_main, container, false);

        assignCardViewLayoutForSpecificFragment(view);
        assignExpandSwitcherToCardView(view);

        return view;
    }

    private void assignCardViewLayoutForSpecificFragment(View view) {
        header1 = view.findViewById(R.id.header_wrapper);
        header2 = view.findViewById(R.id.header_wrapper_2);
        header3 = view.findViewById(R.id.header_wrapper_3);

        cont1 = view.findViewById(R.id.container);
        cont2 = view.findViewById(R.id.container_2);
        cont3 = view.findViewById(R.id.container_3);

        mCardView1 = (CardView) view.findViewById(R.id.card_view);
        mCardView2 = (CardView) view.findViewById(R.id.card_view_2);
        mCardView3 = (CardView) view.findViewById(R.id.card_view_3);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCardView1 = (CardView) view.findViewById(R.id.card_view);
    }

    private void assignExpandSwitcherToCardView(final View createdView) {
        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSwitcher(createdView, R.id.show_arrow, R.id.container, getLayoutHeight(cont1));
            }
        });

        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSwitcher(createdView, R.id.show_arrow_2, R.id.container_2, getLayoutHeight(cont2));
            }
        });

        header3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSwitcher(createdView, R.id.show_arrow_3, R.id.container_3, getLayoutHeight(cont3));
            }
        });
    }


    /**
     * Animation for expanding switcher
     *
     * @param context           - view we operate on
     * @param switchButton      - arrow image
     * @param elementToShowHide - fragment container to hide
     * @param heightOriginal    - original height to retrieve after click on expander button
     */
    private void expandSwitcher(View context, int switchButton, int elementToShowHide, int heightOriginal) {
        final ImageView switcher = (ImageView) context.findViewById(switchButton);
        int tag = Integer.parseInt(switcher.getTag().toString());
        final View wrapper = context.findViewById(elementToShowHide);
        ValueAnimator animator = null;

        if (tag == 1) {
            animator = slideAnimator(wrapper, heightOriginal, 0);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    wrapper.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            animator.start();

            switcher.setTag("0");
            switcher.setImageResource(R.drawable.ic_expand_less_grey);
        } else {
            wrapper.setVisibility(View.VISIBLE);
            animator = slideAnimator(wrapper, 0, heightOriginal);
            animator.start();

            switcher.setTag("1");
            switcher.setImageResource(R.drawable.ic_expand_more_grey);
        }
    }

    /**
     * Animation of fragment
     *
     * @param element - view we operate on
     * @param start   -  start point
     * @param end     - end point
     * @return
     */
    private ValueAnimator slideAnimator(final View element, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = element.getLayoutParams();
                layoutParams.height = value;
                element.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    /**
     * Layout height based on device
     *
     * @param linearLayout - view we operate on
     * @return
     */
    private int getLayoutHeight(View linearLayout) {
        WindowManager wm = (WindowManager) linearLayout.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int deviceWidth;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            deviceWidth = size.x;
        } else {
            deviceWidth = display.getWidth();
        }

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        linearLayout.measure(widthMeasureSpec, heightMeasureSpec);
        return linearLayout.getMeasuredHeight();
    }

    public void setWidgetListener(WidgetInterface widgetListener) {
        this.widgetListener = widgetListener;
    }

    public WidgetInterface getWidgetListener() {
        return widgetListener;
    }

    interface WidgetInterface {
        public void refresh();

        public void redraw();
    }

}
