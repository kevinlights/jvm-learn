package com.gjh.learn.jvm.memory;

import com.alibaba.fastjson.JSON;
import com.gjh.learn.jvm.utils.Print;
import org.netbeans.lib.profiler.heap.*;
import org.netbeans.modules.profiler.oql.engine.api.OQLEngine;
import org.netbeans.modules.profiler.oql.engine.api.OQLException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class OQLDumpAnalysis {
    public static final String dumpFile = "dump/student_oom.hprof";

    public static void main(String[] args) throws IOException, OQLException {
        OQLEngine engine = new OQLEngine(HeapFactory.createHeap(new File(dumpFile)));
        Heap heap = engine.getHeap();
        List allClasses = heap.getAllClasses();
        Print.BLACK("Class: {}", allClasses.size());

        HeapSummary summary = heap.getSummary();
        Print.BLACK("summary: {}", JSON.toJSONString(summary));

        Properties properties = heap.getSystemProperties();
        Print.BLACK("System properties size: {}", properties.size());
        Print.BLACK("java.home: {}", properties.getProperty("java.home"));

        Object c1 = allClasses.get(0);
        Print.BLACK("first class type: {}", c1.getClass());
        if (c1 instanceof org.netbeans.lib.profiler.heap.JavaClass) {
            org.netbeans.lib.profiler.heap.JavaClass javaClass1 = (JavaClass) c1;
            Print.BLACK("first class name: {}", javaClass1.getName());
            Print.BLACK("first class instances count: {}", javaClass1.getInstancesCount());
            List instances = javaClass1.getInstances();
            Object i1 = instances.get(0);
            Print.BLACK("first instance type: {}", i1.getClass());
            if (i1 instanceof Instance) {
                Instance instance = (Instance) i1;
                /*Object content = instance.getValueOfField("content");
                if (content instanceof Instance) {
                    Object value = ((Instance) content).getValueOfField("value");
                    if (value instanceof PrimitiveArrayInstance) {
                        List values = ((PrimitiveArrayInstance) value).getValues();
                        Print.GREEN("values = {}",  values);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < values.size(); i++) {
                            // Print.BLACK(values.get(i).getClass());
                            if (values.get(i) instanceof String) {
                                sb.append(values.get(i));
                            }
                        }
                        Print.RED(sb);
                    }
                }*/
                List fieldValues = instance.getFieldValues();
                for (Object o1 : fieldValues) {
                    // Print.GREEN("field1: {}", f1.getClass());
                    if (o1 instanceof ObjectFieldValue) {
                        ObjectFieldValue ofv1 = (ObjectFieldValue) o1;

                        Object value = ofv1.getInstance().getValueOfField("value");
                        if (value instanceof PrimitiveArrayInstance) {
                            List values = ((PrimitiveArrayInstance) value).getValues();
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < values.size(); i++) {
                                // Print.BLACK(values.get(i).getClass());
                                if (values.get(i) instanceof String) {
                                    sb.append(values.get(i));
                                }
                            }
                            Print.GREEN("first level -> declaring class: {}, field class: {}, field: {}, value: {}, instance number: {}, instance id: {}, value of field: {}, value type: {}", ofv1.getField().getDeclaringClass().getName(), ofv1.getField().getType().getName(), ofv1.getField().getName(), ofv1.getValue(), ofv1.getInstance().getInstanceNumber(), ofv1.getInstance().getInstanceId(), sb, ((PrimitiveArrayInstance) value).getJavaClass().getName());
                        }

                        /*for (Object o2 : ofv1.getInstance().getFieldValues()) {
                            if (o2 instanceof ObjectFieldValue) {
                                ObjectFieldValue ofv2 = (ObjectFieldValue) o2;
                                Print.BLUE("second level -> declaring class: {}, field class: {}, field: {}, value: {}, instance number: {}", ofv2.getField().getDeclaringClass().getName(), ofv2.getField().getType().getName(), ofv2.getField().getName(), ofv2.getValue(), ofv2.getInstance().getInstanceNumber());

                                for (Object o3 : ofv2.getInstance().getFieldValues()) {
                                    if (o3 instanceof ObjectFieldValue) {
                                        ObjectFieldValue ofv3 = (ObjectFieldValue) o3;
                                        Print.PURPLE("third level -> declaring class: {}, field class: {}, field: {}, value: {}, instance number: {}", ofv3.getField().getDeclaringClass().getName(), ofv3.getField().getType().getName(), ofv3.getField().getName(), ofv3.getValue(), ofv3.getInstance().getInstanceNumber());

                                        for (Object o4 : ofv3.getInstance().getFieldValues()) {
                                            if (o4 instanceof ObjectFieldValue) {
                                                ObjectFieldValue ofv4 = (ObjectFieldValue) o4;
                                                Print.PURPLE("fourth level -> declaring class: {}, field class: {}, field: {}, value: {}, instance number: {}", ofv4.getField().getDeclaringClass().getName(), ofv4.getField().getType().getName(), ofv4.getField().getName(), ofv4.getValue(), ofv4.getInstance().getInstanceNumber());
                                            }
                                        }
                                    }
                                }


                            }
                        }*/

                    }
                }
            }
        }


        List<String> urls = new ArrayList<>();
        engine.executeQuery("select s.url from com.gjh.learn.jvm.memory.WebPage s", o -> {
            // Print.BLACK(o.getClass());
            if (o instanceof Instance) {
                Object value = ((Instance) o).getValueOfField("value");
                // Print.BLACK(value);
                if (value instanceof PrimitiveArrayInstance) {
                    List values = ((PrimitiveArrayInstance) value).getValues();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < values.size(); i++) {
                        // Print.BLACK(values.get(i).getClass());
                        if (values.get(i) instanceof String) {
                            sb.append(values.get(i));
                        }
                    }
                    // Print.BLUE("url: {}", sb);
                    urls.add(sb.toString());
                }
            }
            return false;
        });
        Print.BLACK("URL size: {}", urls.size());

    }
}
