package com.stepienk.libraryapp.view.ebooks;

import info.androidhive.LibraryApp.R;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

/**
 * Created by Krzysiek on 2015-04-06.
 * Controls for displaying each pdf
 */
public class eBookShowPdfActivity extends PdfViewerActivity {

    public int getPreviousPageImageResource() {
        return R.drawable.left_arrow;
    }

    public int getNextPageImageResource() {
        return R.drawable.right_arrow;
    }

    public int getZoomInImageResource() {
        return R.drawable.zoom_in;
    }

    public int getZoomOutImageResource() {
        return R.drawable.zoom_out;
    }

    public int getPdfPasswordLayoutResource() {
        return R.layout.ebooks_details_show_pdf_if_pass;
    }

    public int getPdfPageNumberResource() {
        return R.layout.ebooks_details_show_pdf;
    }

    public int getPdfPasswordEditField() {
        return R.id.etPassword;
    }

    public int getPdfPasswordOkButton() {
        return R.id.btOK;
    }

    public int getPdfPasswordExitButton() {
        return R.id.btExit;
    }

    public int getPdfPageNumberEditField() {
        return R.id.pagenum_edit;
    }
}
