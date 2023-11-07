/*******************************************************************************
 * Copyright (c) 2004, 2023 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package lpg.lpgjavaruntime;

public class ConfigurationElement
{
    ConfigurationElement next;

    StateElement last_element;
    public int stack_top,
               action_length,
               conflict_index,
               curtok,
               act;

    public void retrieveStack(int stack[])
    {
        StateElement tail = last_element;
        for (int i = stack_top; i >= 0; i--)
        {
            stack[i] = tail.number;
            tail = tail.parent;
        }
        return;
    }
};
