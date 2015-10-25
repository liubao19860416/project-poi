package com.saick.base.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/**
 * WORD格式文件
 * 
 * @author Liubao
 * @2014年12月14日
 *
 */
public class WordWriter {

    public static void main(String[] args) {
        boolean b = writeWordFile("d://temp//test.doc", "hello,你好");
        System.out.println(b);
    }
    
    public WordWriter() {
    }

    public static boolean writeWordFile(String path, String content) {
        boolean result = false;
        try {
            // byte b[] = content.getBytes("ISO-8859-1");
            byte b[] = content.getBytes();
            //将数据读到内存中
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(b);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
            DirectoryEntry directory = poifsFileSystem.getRoot();

            DocumentEntry de = directory.createDocument("WordDocument", arrayInputStream);
            File file=new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(path);
            poifsFileSystem.writeFilesystem(outputStream);
            arrayInputStream.close();
            outputStream.close();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
/*
 * public String extractText(InputStream in) throws IOException { ArrayList text
 * = new ArrayList(); POIFSFileSystem fsys = new POIFSFileSystem(in);
 * 
 * DocumentEntry headerProps = (DocumentEntry)
 * fsys.getRoot().getEntry("WordDocument"); DocumentInputStream din =
 * fsys.createDocumentInputStream("WordDocument"); byte[] header = new
 * byte[headerProps.getSize()];
 * 
 * din.read(header); din.close(); // Prende le informazioni dall'header del
 * documento int info = LittleEndian.getShort(header, 0xa);
 * 
 * boolean useTable1 = (info & 0x200) != 0;
 * 
 * //boolean useTable1 = true;
 * 
 * // Prende informazioni dalla piece table int complexOffset =
 * LittleEndian.getInt(header, 0x1a2); //int complexOffset =
 * LittleEndian.getInt(header);
 * 
 * String tableName = null; if (useTable1) { tableName = "1Table"; } else {
 * tableName = "0Table"; }
 * 
 * DocumentEntry table = (DocumentEntry) fsys.getRoot().getEntry(tableName);
 * byte[] tableStream = new byte[table.getSize()];
 * 
 * din = fsys.createDocumentInputStream(tableName);
 * 
 * din.read(tableStream); din.close();
 * 
 * din = null; fsys = null; table = null; headerProps = null;
 * 
 * int multiple = findText(tableStream, complexOffset, text);
 * 
 * StringBuffer sb = new StringBuffer(); int size = text.size(); tableStream =
 * null;
 * 
 * for (int x = 0; x < size; x++) {
 * 
 * WordTextPiece nextPiece = (WordTextPiece) text.get(x); int start =
 * nextPiece.getStart(); int length = nextPiece.getLength();
 * 
 * boolean unicode = nextPiece.usesUnicode(); String toStr = null; if (unicode)
 * { toStr = new String(header, start, length * multiple, "UTF-16LE"); } else {
 * toStr = new String(header, start, length, "ISO-8859-1"); }
 * sb.append(toStr).append(" ");
 * 
 * } return sb.toString(); }
 * 
 * private static int findText(byte[] tableStream, int complexOffset, ArrayList
 * text) throws IOException { //actual text int pos = complexOffset; int
 * multiple = 2; //skips through the prms before we reach the piece table. These
 * contain data //for actual fast saved files while (tableStream[pos] == 1) {
 * pos++; int skip = LittleEndian.getShort(tableStream, pos); pos += 2 + skip; }
 * if (tableStream[pos] != 2) { throw new IOException("corrupted Word file"); }
 * else { //parse out the text pieces int pieceTableSize =
 * LittleEndian.getInt(tableStream, ++pos); pos += 4; int pieces =
 * (pieceTableSize - 4) / 12; for (int x = 0; x < pieces; x++) { int filePos =
 * LittleEndian.getInt(tableStream, pos + ((pieces + 1) * 4) + (x *<img
 * src="/images/forum/smiles/icon_cool.gif"/> + 2); boolean unicode = false; if
 * ((filePos & 0x40000000) == 0) { unicode = true; } else { unicode = false;
 * multiple = 1; filePos &= ~(0x40000000); //gives me FC in doc stream filePos
 * /= 2; } int totLength = LittleEndian.getInt(tableStream, pos + (x + 1) * 4) -
 * LittleEndian.getInt(tableStream, pos + (x * 4));
 * 
 * WordTextPiece piece = new WordTextPiece(filePos, totLength, unicode);
 * text.add(piece);
 * 
 * }
 * 
 * } return multiple; } public static void main(String[] args){ WordTest w = new
 * WordTest(); POIFSFileSystem ps = new POIFSFileSystem(); try{
 * 
 * File file = new File("C:\\test.doc");
 * 
 * InputStream in = new FileInputStream(file); String s = w.extractText(in);
 * System.out.println(s);
 * 
 * 
 * }catch(Exception e){ e.printStackTrace(); }
 * 
 * } public boolean writeWordFile(String path, String content) { boolean w =
 * false; try {
 * 
 * // byte b[] = content.getBytes("ISO-8859-1"); byte b[] = content.getBytes();
 * 
 * ByteArrayInputStream bais = new ByteArrayInputStream(b);
 * 
 * POIFSFileSystem fs = new POIFSFileSystem(); DirectoryEntry directory =
 * fs.getRoot();
 * 
 * DocumentEntry de = directory.createDocument("WordDocument", bais);
 * 
 * FileOutputStream ostream = new FileOutputStream(path);
 * 
 * fs.writeFilesystem(ostream);
 * 
 * bais.close(); ostream.close();
 * 
 * } catch (IOException e) { e.printStackTrace(); }
 * 
 * return w; }
 * 
 * }
 * 
 * class WordTextPiece { private int _fcStart; private boolean _usesUnicode;
 * private int _length;
 * 
 * public WordTextPiece(int start, int length, boolean unicode) { _usesUnicode =
 * unicode; _length = length; _fcStart = start; } public boolean usesUnicode() {
 * return _usesUnicode; }
 * 
 * public int getStart() { return _fcStart; } public int getLength() { return
 * _length; }
 * 
 * }
 */
