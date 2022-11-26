package com.epam.lab.shop.filter.gzip.wrapper;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.gzip.GzipServletOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GzipServletResponseWrapper extends HttpServletResponseWrapper {

    private ServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;

    public GzipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        HttpServletResponse response = (HttpServletResponse) this.getResponse();
        String type = (response != null) ? response.getContentType() : null;
        if (type != null && type.toLowerCase().startsWith(Constant.TEXT_HTML)) {
            response.setHeader(Constant.CONTENT_ENCODING, Constant.GZIP);
            if (this.printWriter != null) {
                throw new IllegalStateException(Constant.CANNOT_GET_OUTPUT_STREAM);
            }
            if (this.gzipOutputStream == null) {
                this.gzipOutputStream = new GzipServletOutputStream(getResponse().getOutputStream());
            }
            return this.gzipOutputStream;
        } else {
            return super.getOutputStream();
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        HttpServletResponse response = (HttpServletResponse) this.getResponse();
        String type = (response != null) ? response.getContentType() : null;
        if (type != null && type.toLowerCase().startsWith(Constant.TEXT_HTML)) {
            response.setHeader(Constant.CONTENT_ENCODING, Constant.GZIP);
            if (this.printWriter == null && this.gzipOutputStream != null) {
                throw new IllegalStateException(Constant.CANNOT_GET_PRINT_WRITER);
            }
            if (this.printWriter == null) {
                this.gzipOutputStream = new GzipServletOutputStream(getResponse().getOutputStream());
                this.printWriter = new PrintWriter(
                        new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
            }
            return this.printWriter;
        } else {
            return super.getWriter();
        }
    }

    public void close() throws IOException {
        if (this.printWriter != null) {
            this.printWriter.close();
        }
        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.close();
        }
    }
}