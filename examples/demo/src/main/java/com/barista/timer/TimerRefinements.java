package com.barista.timer;

import java.util.Date;
import java.util.TimerTask;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@StateSet({"created", "scheduled", "canceled"})
@RefinementAlias("Period(int x) {_ > 0}")
@RefinementAlias("Delay(int x) {_ >= 0}")
@ExternalRefinementsFor("java.util.Timer")
public interface TimerRefinements {

    @StateRefinement(to="created(this)")
    public void Timer();
    
    @StateRefinement(to="created(this)")
    public void Timer(String name);
    
    @StateRefinement(to="created(this)")
    public void Timer(String name, boolean isDaemon);

    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void schedule(TimerTask task, @Refinement("Delay(_)") long delay);

    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void schedule(TimerTask task, Date time);

    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void schedule(TimerTask task, 
                        @Refinement("Delay(_)") long delay, 
                        @Refinement("Period(_)")long period);


    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void schedule(TimerTask task, Date time, 
                        @Refinement("Period(_)")long period);

    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void scheduleAtFixedRate(TimerTask task, 
                        @Refinement("Delay(_)") long delay, 
                        @Refinement("Period(_)")long period);

    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="scheduled(this)")
    public void scheduleAtFixedRate(TimerTask task, Date time, 
                        @Refinement("Period(_)")long period);
    
    @StateRefinement(from="created(this) || canceled(this) || scheduled(this)", to="canceled(this)")
    public void cancel();
    
    // Keeps the same state, nothing changes
    public void purge();
}
