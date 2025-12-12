
package com.throwable;

import java.io.PrintStream;
import java.io.PrintWriter;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@StateSet({"start", "hasMessage", "hasCause"})
@ExternalRefinementsFor("java.lang.Throwable")
public interface ThrowableRefinements {

    // ##### Constructors #######

    @StateRefinement(to="start(this)")
    public void Throwable();

    @StateRefinement(to="hasMessage(this)")
    public void Throwable(String message);

    @StateRefinement(to="hasCause(this)")
    public void Throwable(String message, Throwable cause);

    @StateRefinement(to="hasCause(this)")
    public void Throwable(Throwable cause);

    @StateRefinement(from="!hasCause(this)", to="hasCause(this)")
    public Throwable initCause(Throwable cause);

    // ##### Getters #######
    @StateRefinement(from="hasCause(this)")
    @StateRefinement(from="hasMessage(this)")
    public String getMessage();
    @StateRefinement(from="hasCause(this)")
    @StateRefinement(from="hasMessage(this)")
    public String getLocalizedMessage();
    @StateRefinement(from="hasCause(this)")
    public Throwable getCause();


    // ##### Other #######
    // public String toString()
    // public void printStackTrace()
    // public void printStackTrace(PrintStream s)
    // public void printStackTrace(PrintWriter s)
    // public Throwable fillInStackTrace()
    // public StackTraceElement[] getStackTrace()
    // public void setStackTrace(StackTraceElement[] stackTrace)
    // public final void addSuppressed(Throwable exception)
    // public final Throwable[] getSuppressed()


}