/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * 修改了getMimeType()方法 ，
 * 将return "application/vnd.ms-excel";
 * 改成了return "application/vnd.ms-excel;charset=ISO-8859-1";
 * 解决resin服务器导出时报错的问题
 */

package org.displaytag.export.excel;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.jsp.JspException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.TableModel;
import org.displaytag.render.HssfTableWriter;

/**
 * Excel exporter using POI.
 * 
 * @author Jorge L. Barroso
 * @version $Revision$ ($Author$)
 */
public class DefaultHssfExportView implements BinaryExportView
{
    /**
     * TableModel to render.
     */
    protected TableModel model;
    
    /**
     * @see org.displaytag.export.BinaryExportView#doExport(java.io.OutputStream)
     */
    public void doExport(OutputStream out) throws IOException, JspException
    {
        try
        {
            HSSFWorkbook wb = new HSSFWorkbook();
            getHssfTableWriter(wb).writeTable(this.model, "-1");
            wb.write(out);
        }
        catch (Exception e)
        {
            throw new HssfGenerationException(e);
        }
    }

    /**
     * Convenience method.
     * @param wb
     * @return
     */
    protected HssfTableWriter getHssfTableWriter(HSSFWorkbook wb)
    {
        return new HssfTableWriter(wb);
    }

    /** 
     * @see org.displaytag.export.ExportView#setParameters(org.displaytag.model.TableModel, boolean, boolean, boolean)
     */
    public void setParameters(TableModel model, boolean exportFullList,
            boolean includeHeader, boolean decorateValues)
    {
        this.model = model;
    }

    /**
     * @see org.displaytag.export.BaseExportView#getMimeType()
     * @return "application/vnd.ms-excel"
     */
    public String getMimeType()
    {
        return "application/vnd.ms-excel;charset=ISO-8859-1"; //$NON-NLS-1$
    }
    
    /**
     * Wraps POI-generated exceptions.
     * @author Fabrizio Giustina
     * @version $Revision$ ($Author$)
     */
    static class HssfGenerationException extends BaseNestableJspTagException
    {
        /**
         * D1597A17A6.
         */
        private static final long serialVersionUID = 899149338534L;

        /**
         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
         * @param cause Previous exception
         */
        public HssfGenerationException(Throwable cause)
        {
            super(DefaultHssfExportView.class, Messages.getString("DefaultHssfExportView.errorexporting"), cause); //$NON-NLS-1$
        }

        /**
         * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
         */
        public SeverityEnum getSeverity()
        {
            return SeverityEnum.ERROR;
        }
    }
}
