package com.vocalabs.egtest.processor.junit;

import com.vocalabs.egtest.processor.AnnotationCollector;
import com.vocalabs.egtest.processor.EgTestWriter;
import com.vocalabs.egtest.processor.MessageHandler;
import com.vocalabs.egtest.processor.data.Example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Build JUnit test source code; this is the entry point. */
public class JUnitWriter implements EgTestWriter {

    private final File directoryToFill;
    private final AlreadyExistsBehavior directoryExistsBehavior;

    public JUnitWriter(File directoryToFill, AlreadyExistsBehavior directoryExistsBehavior) {
        this.directoryToFill = directoryToFill;
        this.directoryExistsBehavior = directoryExistsBehavior;
    }

    @Override
    public void write(AnnotationCollector annotationCollector) throws Exception {
        if (AlreadyExistsBehavior.FAIL.equals(directoryExistsBehavior) && directoryToFill.exists()) {
            annotationCollector.getMessageHandler()
                    .error("EgTest target directory exists ("+directoryToFill.getAbsolutePath()+")");
            return;
        }

        boolean didCreateDir = directoryToFill.mkdirs();
        if (AlreadyExistsBehavior.DELETE.equals(directoryExistsBehavior) &&  ! didCreateDir) {
            File[] files = directoryToFill.listFiles();
            if (files == null)
                throw new IOException("Location for writing EgTest source is not a directory: "+directoryToFill);
            for (File f: files) {
                deleteRecursive(f);
            }
        }

        MessageHandler messageHandler = annotationCollector.getMessageHandler();
        Map<String, List<Example<?>>> itemsByClassName = annotationCollector.getItemsByClassName();
        for (Map.Entry<String, List<Example<?>>> entry: itemsByClassName.entrySet()) {
            JUnitClassWriter.createFileSpec(entry.getKey(), messageHandler, entry.getValue())
                .writeTo(directoryToFill);
        }
    }

    private void deleteRecursive(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f: files) {
                deleteRecursive(f);
            }
        }
        file.delete();
    }
}
