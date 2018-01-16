/** Created on 2006-1-10
 *
 * Copyright (c) Sybase, Inc. 2004-2006   
 * All rights reserved.                                    
 */

package org.eclipse.datatools.sqltools.internal.externalfile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.sqltools.sqleditor.SQLStorageAnnotationModel;


/**
 * @author yjwang
 *
 * This is a concreate AnnotationModel class for External SQL file.
 * We can add more feature into this file by-and-by
 */
public class ExternalSQLFileAnnotationModel extends SQLStorageAnnotationModel
{

    public ExternalSQLFileAnnotationModel(IPath path)
    {
        super(new ExternalFileStorage(path));
    }

}
