package de.fklappan.app.webnotes.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Custom scope because dagger needs a unique scope per module (using @Singleton on every component
 * is prohibited).
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerScope {
}
