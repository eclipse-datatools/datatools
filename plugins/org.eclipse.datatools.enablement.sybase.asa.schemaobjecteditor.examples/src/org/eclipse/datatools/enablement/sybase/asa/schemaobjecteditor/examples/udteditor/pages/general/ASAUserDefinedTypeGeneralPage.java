/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.pages.general;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.PageBook;

/**
 * @author renj
 */
public class ASAUserDefinedTypeGeneralPage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    protected SybaseASABaseUserDefinedType _mainObject;
    protected ISchemaObjectEditModel       _editModelObject;

    private Label                          _dbOwner;
    private Text                           _checkConstraintText;
    private Text                           _uddNameText;
    private Text                           _uddDefaultValueText;
    private PageBook                       _numericComp;
    private Composite                      _blankComp;
    private Composite                      _lengthComp;
    private Composite                      _precisionComp;
    private Composite                      _precisionFloatComp;
    private Text                           _sysDatatypeText;
    private Text                           _lengthText;
    private Text                           _precisionText;
    private Text                           _scaleText;
    private Text                           _precisionFloatText;
    private Text                           _nullSettingText;

    public ASAUserDefinedTypeGeneralPage()
    {
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        Composite comp = managedForm.getForm().getBody();
        managedForm.getForm().setText(Messages.ASAUDDEditorPage_general); //$NON-NLS-1$
        GridLayout layout = new GridLayout();
        comp.setLayout(layout);

        FormToolkit toolkit = managedForm.getToolkit();
        Composite container = toolkit.createComposite(comp);
        layout = new GridLayout();
        layout.numColumns = 2;
        container.setLayout(layout);
        GridData gd = new GridData(GridData.FILL_BOTH);
        container.setLayoutData(gd);

        Label dbOwnerLabel = toolkit.createLabel(container, Messages.ASAUDDEditorPage_owner, SWT.NONE);
        dbOwnerLabel.setForeground(toolkit.getColors().getColor(FormColors.TITLE));
        gd = new GridData();
        dbOwnerLabel.setLayoutData(gd);

        _dbOwner = toolkit.createLabel(container, "", SWT.NONE);

        Label uddNameLabel = toolkit.createLabel(container, Messages.ASAUDDEditorPage_name, SWT.NONE);
        uddNameLabel.setForeground(toolkit.getColors().getColor(FormColors.TITLE));
        _uddNameText = toolkit.createText(container, "", SWT.READ_ONLY);
        _uddNameText.setEnabled(false);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        _uddNameText.setLayoutData(gd);

        Label blankLabel = toolkit.createLabel(container, "", SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        blankLabel.setLayoutData(gd);

        // Setting group
        createSettingSection(toolkit, container);

        blankLabel = toolkit.createLabel(container, "", SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        blankLabel.setLayoutData(gd);

        // Check constraint group
        createConstraintSection(toolkit, container);

        initControls();

        toolkit.paintBordersFor(container);
    }

    protected void createSettingSection(FormToolkit toolkit, Composite container)
    {
        CollapseableSection setting = new CollapseableSection(toolkit, Messages.ASAUDDEditorPage_settings, container
                .getDisplay(), false, false, SWT.NONE)
        {
            public void createSectionContent(Composite parent)
            {
                Composite _settingsGrp = parent;
                GridData gd = new GridData(GridData.FILL_HORIZONTAL);
                getSection().setLayoutData(gd);
                GridLayout layout = new GridLayout();
                layout.numColumns = 3;
                _settingsGrp.setLayout(layout);

                // System datatype: [ combo ] Length: [spinner]
                // Null Identity Setting: [ combo ]
                Label sysDatatypeLabel = _toolkit.createLabel(_settingsGrp, Messages.ASAUDDEditorPage_system_datatype,
                        SWT.NONE);
                sysDatatypeLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _sysDatatypeText = _toolkit.createText(_settingsGrp, "", SWT.READ_ONLY);
                _sysDatatypeText.setEnabled(false);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                _sysDatatypeText.setLayoutData(gd);

                _numericComp = new PageBook(_settingsGrp, SWT.NONE);
                gd = new GridData();
                _numericComp.setLayoutData(gd);

                // blank group
                _blankComp = _toolkit.createComposite(_numericComp, SWT.NONE);
                _blankComp.setLayoutData(new GridData());
                _blankComp.setLayout(new GridLayout());
                Text blankText = _toolkit.createText(_blankComp, "", SWT.READ_ONLY);
                gd = new GridData();
                gd.widthHint = 0;
                blankText.setLayoutData(gd);
                blankText.setVisible(false);

                // length group
                _lengthComp = _toolkit.createComposite(_numericComp, SWT.NONE);
                _lengthComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                layout = new GridLayout();
                layout.numColumns = 2;
                _lengthComp.setLayout(layout);
                Label lengthLabel = _toolkit.createLabel(_lengthComp, Messages.ASAUDDEditorPage_length, SWT.NONE);
                lengthLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _lengthText = _toolkit.createText(_lengthComp, "", SWT.READ_ONLY);
                _lengthText.setEnabled(false);
                gd = new GridData();
                gd.widthHint = 30;
                _lengthText.setLayoutData(gd);

                // precision group
                _precisionFloatComp = _toolkit.createComposite(_numericComp, SWT.NONE);
                _precisionFloatComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                layout = new GridLayout();
                layout.numColumns = 2;
                _precisionFloatComp.setLayout(layout);
                Label precisionFloatLabel = _toolkit.createLabel(_precisionFloatComp,
                        Messages.ASAUDDEditorPage_precision, SWT.NONE);
                precisionFloatLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _precisionFloatText = _toolkit.createText(_precisionFloatComp, "", SWT.READ_ONLY);
                _precisionFloatText.setEnabled(false);
                gd = new GridData();
                gd.widthHint = 30;
                _precisionFloatText.setLayoutData(gd);

                // precision and scale group
                _precisionComp = _toolkit.createComposite(_numericComp, SWT.NONE);
                _precisionComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                layout = new GridLayout();
                layout.numColumns = 4;
                _precisionComp.setLayout(layout);
                Label precisionLabel = _toolkit.createLabel(_precisionComp, Messages.ASAUDDEditorPage_precision,
                        SWT.NONE);
                precisionLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _precisionText = _toolkit.createText(_precisionComp, "", SWT.READ_ONLY);
                _precisionText.setEnabled(false);
                gd = new GridData();
                gd.widthHint = 20;
                _precisionText.setLayoutData(gd);
                Label scaleLabel = _toolkit.createLabel(_precisionComp, Messages.ASAUDDEditorPage_scale, SWT.NONE);
                scaleLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _scaleText = _toolkit.createText(_precisionComp, "", SWT.READ_ONLY);
                _scaleText.setEnabled(false);
                gd = new GridData();
                gd.widthHint = 20;
                _scaleText.setLayoutData(gd);

                Label nullSettingLabel = _toolkit.createLabel(_settingsGrp, Messages.ASAUDDEditorPage_null_indentity,
                        SWT.NONE);
                nullSettingLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _nullSettingText = _toolkit.createText(_settingsGrp, "", SWT.READ_ONLY);
                _nullSettingText.setEnabled(false);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                _nullSettingText.setLayoutData(gd);

                Label blankLabel = _toolkit.createLabel(_settingsGrp, "", SWT.NONE);
                blankLabel.setLayoutData(new GridData());

                blankLabel = _toolkit.createLabel(_settingsGrp, "", SWT.NONE);
                gd = new GridData();
                gd.heightHint = 1;
                gd.horizontalSpan = 3;
                blankLabel.setLayoutData(gd);

                Label uddDefaultValueLabel = _toolkit.createLabel(_settingsGrp,
                        Messages.ASAUDDEditorPage_default_value, SWT.NONE);
                uddDefaultValueLabel.setForeground(_toolkit.getColors().getColor(FormColors.TITLE));
                _uddDefaultValueText = _toolkit.createText(_settingsGrp, "", SWT.READ_ONLY);
                _uddDefaultValueText.setEnabled(false);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                _uddDefaultValueText.setLayoutData(gd);

                blankLabel = _toolkit.createLabel(_settingsGrp, "", SWT.NONE);
                blankLabel.setLayoutData(new GridData());

                _toolkit.paintBordersFor(_settingsGrp);
                _toolkit.paintBordersFor(_numericComp);
                _toolkit.paintBordersFor(_blankComp);
                _toolkit.paintBordersFor(_lengthComp);
                _toolkit.paintBordersFor(_precisionComp);
                _toolkit.paintBordersFor(_precisionFloatComp);
            }
        };
        setting.createControl(container, 2, null);
    }

    protected void createConstraintSection(FormToolkit toolkit, Composite container)
    {
        CollapseableSection binding = new CollapseableSection(toolkit, Messages.ASAUDDEditorPage_check_constraint,
                container.getDisplay(), false, false, SWT.NONE)
        {
            public void createSectionContent(Composite parent)
            {
                GridData gd = new GridData(GridData.FILL_BOTH);
                getSection().setLayoutData(gd);
                GridLayout layout = new GridLayout();
                parent.setLayout(layout);

                _checkConstraintText = _toolkit.createText(parent, "", SWT.NONE | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
                        | SWT.READ_ONLY);
                _checkConstraintText.setEnabled(false);
                gd = new GridData(GridData.FILL_BOTH);
                _checkConstraintText.setLayoutData(gd);

                _toolkit.paintBordersFor(parent);
            }
        };
        binding.createControl(container, 2, null);
    }

    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        setPageInput(input);
    }

    public void setPageInput(IEditorInput input)
    {
        if (input instanceof SchemaObjectEditorInput)
        {
            _editModelObject = ((SchemaObjectEditorInput) input).getEditModelObject();
            _mainObject = (SybaseASABaseUserDefinedType) _editModelObject.getMainSQLObject();
        }
    }

    protected void initControls()
    {
        _dbOwner.setText(_mainObject.getSchema().getName());

        _uddNameText.setText(_mainObject.getName());

        // for system type
        PredefinedDataType pdt = _mainObject.getPredefinedRepresentation();
        String[] pdtResult = interpretPredefinedDataType(ModelUtil.getDatabaseDefinition(_mainObject)
                .getPredefinedDataTypeFormattedName(pdt));

        if (pdtResult[0] != null && !pdtResult[0].equals(""))
        {
            _sysDatatypeText.setText(pdtResult[0]);
            PredefinedDataTypeDefinition pdtd = ModelUtil.getDatabaseDefinition(_mainObject)
                    .getPredefinedDataTypeDefinition(pdtResult[0]);
            if (!pdtd.isLengthSupported() && !pdtd.isPrecisionSupported() && !pdtd.isScaleSupported())
            {
                _numericComp.showPage(_blankComp);
            }
            if (pdtd.isLengthSupported() && !pdtd.isPrecisionSupported() && !pdtd.isScaleSupported())
            {
                _lengthText.setText(pdtResult[1]);
                _numericComp.showPage(_lengthComp);
            }

            if (!pdtd.isLengthSupported() && pdtd.isPrecisionSupported() && pdtd.isScaleSupported())
            {
                _scaleText.setText(pdtResult[2]);
                _precisionText.setText(pdtResult[1]);
                _numericComp.showPage(_precisionComp);
            }

            if (!pdtd.isLengthSupported() && pdtd.isPrecisionSupported() && !pdtd.isScaleSupported())
            {
                _precisionFloatText.setText(pdtResult[1]);
                _numericComp.showPage(_precisionFloatComp);
            }
        }

        // for null setting
        AllowNullType allowNullType = _mainObject.getNullable();
        if (allowNullType.getValue() == AllowNullType.NULLABLE)
        {
            _nullSettingText.setText(Messages.NullIdentitySettings_allow_null);
        }
        else if (allowNullType.getValue() == AllowNullType.NOT_NULLABLE)
        {
            _nullSettingText.setText(Messages.NullIdentitySettings_not_allow_null);
        }
        else
        {
            _nullSettingText.setText(Messages.NullIdentitySettings_default_allow_null);
        }

        // for default value
        _uddDefaultValueText.setText(_mainObject.getDefaultValue() == null ? "" : _mainObject.getDefaultValue());

        // for check constraint
        EList constraints = _mainObject.getConstraint();
        if (constraints != null && constraints.size() > 0)
        {
            CheckConstraint checkConstraint = (CheckConstraint) constraints.get(0);
            if (checkConstraint.getSearchCondition().getSQL() != null
                    && !checkConstraint.getSearchCondition().getSQL().trim().equals(""))
            {
                _checkConstraintText.setText(checkConstraint.getSearchCondition().getSQL());
            }
        }
    }

    protected SQLObject[] getSQLObjects()
    {
        if (_mainObject != null)
        {
            return new SQLObject[]
            {
                _mainObject
            };
        }
        return super.getSQLObjects();
    }

    public String[] interpretPredefinedDataType(String pdt)
    {

        String[] result = new String[3];
        if (pdt == null)
        {
            return result;
        }

        pdt = pdt.toLowerCase();

        if (pdt.indexOf("(") > 0)
        {
            result[0] = pdt.substring(0, pdt.indexOf("("));
            if (pdt.indexOf(",") > 0)
            {
                result[1] = pdt.substring(pdt.indexOf("(") + 1, pdt.indexOf(","));
                result[2] = pdt.substring(pdt.indexOf(",") + 1, pdt.indexOf(")"));
            }
            else
            {
                result[1] = pdt.substring(pdt.indexOf("(") + 1, pdt.indexOf(")"));
            }
        }
        else
        {
            result[0] = pdt;
        }

        return result;
    }
}
