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

public class DeterministicParser extends Stacks
{
    private Monitor monitor = null;
    private int START_STATE,
                NUM_RULES,
                LA_STATE_OFFSET,
                ACCEPT_ACTION,
                ERROR_ACTION;

    private int lastToken,
                currentAction;
    public final int getCurrentRule()     { return currentAction; }
    public final int getFirstToken()      { return getToken(1); }
    public final int getFirstToken(int i) { return getToken(i); }
    public final int getLastToken()       { return lastToken; }
    public final int getLastToken(int i)  { return (i >= prs.rhs(currentAction)
                                                       ? lastToken
                                                       : tokStream.getPrevious(getToken(i + 1))); }
    private TokenStream tokStream;
    private ParseTable prs;
    private RuleAction ra;

    public DeterministicParser(TokenStream tokStream, ParseTable prs, RuleAction ra) throws BadParseSymFileException,NotDeterministicParseTableException
    {
        this.tokStream = tokStream;
        this.prs = prs;
        this.ra = ra;

        START_STATE = prs.getStartState();
        NUM_RULES = prs.getNumRules();
        LA_STATE_OFFSET = prs.getLaStateOffset();
        ACCEPT_ACTION = prs.getAcceptAction();
        ERROR_ACTION = prs.getErrorAction();

        if (! prs.isValidForParser()) throw new BadParseSymFileException();
        if (prs.getBacktrack()) throw new NotDeterministicParseTableException();
    }

    public DeterministicParser(Monitor monitor, TokenStream tokStream, ParseTable prs, RuleAction ra) throws BadParseSymFileException,NotDeterministicParseTableException
    {
        this(tokStream, prs, ra);
        this.monitor = monitor;
    }

    //
    // Process reductions and continue...
    //
    private final void processReductions()
    {
        do
        {
            stateStackTop -= (prs.rhs(currentAction) - 1);
            ra.ruleAction(currentAction);
            currentAction = prs.ntAction(stateStack[stateStackTop], prs.lhs(currentAction));
        } while(currentAction <= NUM_RULES);

        return;
    }

    //
    //
    //
    public Object parse() throws BadParseException
    {
        //
        // Reset the token stream and get the first token.
        //
        tokStream.reset();
        int curtok = tokStream.getToken(),
            current_kind = tokStream.getKind(curtok);
        lastToken = tokStream.getPrevious(curtok);
 
        //
        // Start parsing.
        //
        reallocateStacks(); // make initial allocatation
        stateStackTop = -1;
        currentAction = START_STATE;
        ProcessTerminals: for (;;)
        {
            //
            // if the parser needs to stop processing,
            // it may do so here.
            //
            if (monitor != null && monitor.isCancelled())
                return null;

            try
            {
                stateStack[++stateStackTop] = currentAction;
            }
            catch(IndexOutOfBoundsException e)
            {
                reallocateStacks();
                stateStack[stateStackTop] = currentAction;
            }

            locationStack[stateStackTop] = curtok;

            currentAction = tAction(currentAction, current_kind);
 
            if (currentAction <= NUM_RULES)
            {
                stateStackTop--; // make reduction look like a shift-reduce
                processReductions();
            }
            else if (currentAction > ERROR_ACTION)
            {
                lastToken = curtok;
                curtok = tokStream.getToken();
                current_kind = tokStream.getKind(curtok);
                currentAction -= ERROR_ACTION;
                processReductions();
            }
            else if (currentAction < ACCEPT_ACTION)
            {
                lastToken = curtok;
                curtok = tokStream.getToken();
                current_kind = tokStream.getKind(curtok);
            }
            else break ProcessTerminals;
        }

        if (currentAction == ERROR_ACTION)
            throw new BadParseException(curtok);

        return parseStack[0];
    }

    private int tAction(int act, int sym)
    {
        act = prs.tAction(act, sym);
        if (act > LA_STATE_OFFSET)
        {
            int next_token = tokStream.peek();
            act = prs.lookAhead(act - LA_STATE_OFFSET, tokStream.getKind(next_token));
            while(act > LA_STATE_OFFSET)
            {
                next_token = tokStream.getNext(next_token);
                act = prs.lookAhead(act - LA_STATE_OFFSET, tokStream.getKind(next_token));
            }
        }
        return act;
    }
}
