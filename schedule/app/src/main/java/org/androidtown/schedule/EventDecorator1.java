package org.androidtown.schedule;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by ohji1 on 2017-07-12.
 */

public class EventDecorator1 implements DayViewDecorator {

    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator1(int color, Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
        this.color = color;
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
        // view.addSpan(new DotSpan(5, Color.BLUE)); // Just this appears.
    }
}
