package com.sfauto.base.global.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ExecProcessUtils {

    static private Logger logger = LogManager.getLogger(ExecProcessUtils.class.getName());

    private static String os = System.getProperty("os.name").toLowerCase();
    private static Process ps;

    public static void executeCommand(String cmdStr, String cmd_url) throws Exception{
        ProcessBuilder pb = null;
        if (os.indexOf("win") >= 0) {
            logger.trace("executeCommand on windows:{}", cmdStr);
            pb = new ProcessBuilder("cmd", "/c", cmdStr);
        } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
            logger.trace("executeCommand for Linux:{}", cmdStr);
            pb = new ProcessBuilder("bash", "-c", cmdStr);
        } else if (os.indexOf("mac") >= 0) {
            logger.trace("executeCommand for mac:{}", cmdStr);
            pb = new ProcessBuilder("bash", "-c", cmdStr);
        }

        pb.redirectErrorStream(true);
        pb.directory(new File(cmd_url));

        ps = pb.start();
        waitForCompletion(ps);
        logger.trace("executeCommand in processing!");
    }

    public static void compress(File toFile, File dir) throws Exception {

        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            throw new Exception("Invalid input directory: " + dir);
        }

        FileOutputStream fos = new FileOutputStream(toFile);
        ZipOutputStream outs = new ZipOutputStream(fos);

        FileSystem fs = new FileSystem(dir);
        Iterator allFiles = fs.getFiles(true).iterator();

        while (allFiles.hasNext()) {
            File srcFile = (File) allFiles.next();
            String filepath = srcFile.getAbsolutePath();
            String dirpath = dir.getAbsolutePath();
            String entryName = filepath.substring(dirpath.length() + 1).replace('\\', '/');
            ZipEntry zipEntry = new ZipEntry(entryName);
            zipEntry.setTime(srcFile.lastModified());
            FileInputStream ins = new FileInputStream(srcFile);
            outs.putNextEntry(zipEntry);
            IOHandler.pipe(ins, outs);
            outs.closeEntry();
            ins.close();
        }
        outs.close();
        logger.trace("zip file[{}] success!", toFile);

    }

    public static void extract(File fromFile, File dir) throws Exception{
        if (dir == null) {
            dir = new File(System.getProperty("user.dir"));
        }
        else if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            logger.warn("Invalid output directory: {}" , dir);
        }

        ZipFile zipFile = new ZipFile(fromFile);
        ArrayList fileEntries = new ArrayList();

        Enumeration zipEntries = zipFile.entries();
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();
            if (zipEntry.isDirectory()) {
                new File(dir, zipEntry.getName()).mkdirs();
            }
            else {
                fileEntries.add(zipEntry);
            }
        }

        Iterator allFiles = fileEntries.iterator();
        while (allFiles.hasNext()) {
            ZipEntry fileEntry = (ZipEntry) allFiles.next();
            File destFile = new File(dir, fileEntry.getName());
            destFile.setLastModified(fileEntry.getTime());
            destFile.getParentFile().mkdirs();
            FileOutputStream outs = new FileOutputStream(destFile);
            InputStream ins = zipFile.getInputStream(fileEntry);
            IOHandler.pipe(ins, outs);
            ins.close();
            outs.close();
        }

        zipFile.close();
        logger.trace("extract file[{}] success!", fromFile.getAbsolutePath());

    }

    public InputStream getInputStream() {
        if (ps == null) {
            logger.trace("the member ps is null!");
            return null;
        }

        return ps.getInputStream();
    }

    private static void waitForCompletion(Process ps) throws Exception {
        final InputStream is = ps.getInputStream();
        ps.waitFor();
        ps.destroy();
        logger.trace("waitFor in processing!");
    }

}
