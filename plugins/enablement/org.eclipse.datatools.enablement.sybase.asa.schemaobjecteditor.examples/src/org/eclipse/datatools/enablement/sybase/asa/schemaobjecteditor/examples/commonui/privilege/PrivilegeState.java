/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.swt.graphics.Image;

/**
 * A standard implementation of privilege state
 * 
 * @author Idull
 */
public class PrivilegeState implements IPrivilegeState
{
    private int       _code;
    private String    _dspString;
    private Image     _image;
    private int _receiverType = USER;

    public PrivilegeState(int code, String dspStr, Image image)
    {
        super();
        this._code = code;
        this._dspString = dspStr;
        this._image = image;
    }

    public PrivilegeState(int code, String dspStr, Image image, int receiverType)
    {
        super();
        this._code = code;
        this._dspString = dspStr;
        this._image = image;
        this._receiverType = receiverType;
    }

    public PrivilegeState(int code, String dspStr)
    {
        super();
        this._code = code;
        this._dspString = dspStr;
        this._image = null;
    }

    public PrivilegeState(int code, String dspStr, int receiverType)
    {
        super();
        this._code = code;
        this._dspString = dspStr;
        this._image = null;
        this._receiverType = receiverType;
    }

    public int getCode()
    {
        return _code;
    }

    public String getDisplayString()
    {
        return _dspString;
    }

    public Image getImage()
    {
        return _image;
    }

    public int getAuthType()
    {
        return _receiverType;
    }

    /**
     * Returns the predefined IPrivilegeState objects containing code and display string.
     * @param code
     * @return
     */
    public static IPrivilegeState get(int code, int receiverType)
    {
        if (receiverType == GROUP)
        {
            switch (code)
            {
                case IPrivilegeState.EMPTY_PRIVILEGE:
                    return IPrivilegeState.GROUP_EMPTY_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_PRIVILEGE:
                    return IPrivilegeState.GROUP_GRANTED_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE:
                    return IPrivilegeState.GROUP_GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE;
                case IPrivilegeState.INHERITED_PRIVILEGE:
                    return IPrivilegeState.GROUP_INHERITED_PRIVILEGE_STATE;
                case IPrivilegeState.REVOKED_INHERITED_PRIVILEGE:
                    return IPrivilegeState.GROUP_REVOKED_INHERITED_PRIVILEGE_STATE;
            }
        }
        else if (receiverType == ROLE)
        {
            switch (code)
            {
                case IPrivilegeState.EMPTY_PRIVILEGE:
                    return IPrivilegeState.ROLE_EMPTY_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_PRIVILEGE:
                    return IPrivilegeState.ROLE_GRANTED_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE:
                    return IPrivilegeState.ROLE_GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE;
                case IPrivilegeState.INHERITED_PRIVILEGE:
                    return IPrivilegeState.ROLE_INHERITED_PRIVILEGE_STATE;
                case IPrivilegeState.REVOKED_INHERITED_PRIVILEGE:
                    return IPrivilegeState.ROLE_REVOKED_INHERITED_PRIVILEGE_STATE;
            }  
        }
        else
        {
            switch (code)
            {
                case IPrivilegeState.EMPTY_PRIVILEGE:
                    return IPrivilegeState.EMPTY_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_PRIVILEGE:
                    return IPrivilegeState.GRANTED_PRIVILEGE_STATE;
                case IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE:
                    return IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE;
                case IPrivilegeState.INHERITED_PRIVILEGE:
                    return IPrivilegeState.INHERITED_PRIVILEGE_STATE;
                case IPrivilegeState.REVOKED_INHERITED_PRIVILEGE:
                    return IPrivilegeState.REVOKED_INHERITED_PRIVILEGE_STATE;
            }
        }
        return null;
    }
}
