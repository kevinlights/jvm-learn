package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.Print;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * created on 2021/3/20
 *
 * -XX:InitialHeapSize=5242880 -XX:MaxHeapSize=20971520 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
 * [GC (Allocation Failure) [DefNew: 1664K->191K(1856K), 0.0018154 secs] 1664K->660K(5952K), 0.0018556 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
 * max mem: 19.375 M
 * free mem: 4.147 M
 * total mem: 5.812 M
 * [GC (Allocation Failure) [DefNew: 1269K->191K(1856K), 0.0017203 secs] 1737K->898K(5952K), 0.0017461 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * alloc 1MB to array [b]
 * max mem: 19.375 M
 * free mem: 3.906 M
 * total mem: 5.812 M
 * [GC (Allocation Failure) [DefNew: 1246K->0K(1856K), 0.0013038 secs][Tenured: 1922K->1922K(4096K), 0.0018031 secs] 1952K->1922K(5952K), [Metaspace: 3917K->3917K(1056768K)], 0.0031602 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * allow 4MB to array [b]
 * max mem: 19.375 M
 * free mem: 3.970 M
 * total mem: 9.878 M
 *
 * max 比设定值略少，存在可用内存的损失 - 回收算法，实际可用内存会浪费 大小等于 from/to 的空间
 * -Xmx - from = max mem
 *
 *
 * @author kevinlights
 */
public class HeapAlloc {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        printMem(rt);

        byte[] b = new byte[1 * 1024 * 1024];
        Print.GREEN("alloc 1MB to array [b]");
        printMem(rt);

        b = new byte[4 * 1024 * 1024];
        Print.GREEN("allow 4MB to array [b]");
        printMem(rt);
    }

    private static void printMem(Runtime rt) {
        Print.BLACK("max mem: {} M", calMem(rt.maxMemory()));
        Print.BLACK("free mem: {} M", calMem(rt.freeMemory()));
        Print.BLACK("total mem: {} M", calMem(rt.totalMemory()));
    }

    /**
     * calculate memory from bytes to MB
     * @param mem
     * @return
     */
    private static String calMem(long mem) {
        DecimalFormat df = new DecimalFormat("#.000");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(mem / 1024.0 / 1024.0);
    }
}
