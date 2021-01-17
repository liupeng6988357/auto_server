package org.auto.plate.utils;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.ExecuteCaseLog;

import java.io.InputStream;
import java.util.List;

public interface ElementOperate {

    ExecuteCaseLog excuteTestCase(String projectaddress, List<AutoElement> elementList,
                                  List<String> paramList, int model, ExecuteCaseLog executeCaseLog, String browserType, List<InputStream> inputStream) throws Exception;

}
