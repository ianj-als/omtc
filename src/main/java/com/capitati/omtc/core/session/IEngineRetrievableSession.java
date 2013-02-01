package com.capitati.omtc.core.session;

import java.util.Set;

import com.capitati.omtc.core.engine.IEngine;
import com.google.common.base.Predicate;

public interface IEngineRetrievableSession {
  Set<IEngine> retrieveEngines(Predicate<IEngine> filter) throws Exception;
}
