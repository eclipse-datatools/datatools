

package org.eclipse.datatools.sqltools.parsers.sql.lexer;





//import lpg.lpgjavaruntime.*;

public class SQLKeywordLexer extends SQLKeywordLexerprs implements SQLParsersym
{
    private char[] inputChars;
    private final int keywordKind[] = new int[688 + 1];

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }


final static int tokenKind[] = new int[128];

static
{
    tokenKind['a'] = Char_A;
    tokenKind['b'] = Char_B;
    tokenKind['c'] = Char_C;
    tokenKind['d'] = Char_D;
    tokenKind['e'] = Char_E;
    tokenKind['f'] = Char_F;
    tokenKind['g'] = Char_G;
    tokenKind['h'] = Char_H;
    tokenKind['i'] = Char_I;
    tokenKind['j'] = Char_J;
    tokenKind['k'] = Char_K;
    tokenKind['l'] = Char_L;
    tokenKind['m'] = Char_M;
    tokenKind['n'] = Char_N;
    tokenKind['o'] = Char_O;
    tokenKind['p'] = Char_P;
    tokenKind['q'] = Char_Q;
    tokenKind['r'] = Char_R;
    tokenKind['s'] = Char_S;
    tokenKind['t'] = Char_T;
    tokenKind['u'] = Char_U;
    tokenKind['v'] = Char_V;
    tokenKind['w'] = Char_W;
    tokenKind['x'] = Char_X;
    tokenKind['y'] = Char_Y;
    tokenKind['z'] = Char_Z;

    tokenKind['A'] = Char_A;
    tokenKind['B'] = Char_B;
    tokenKind['C'] = Char_C;
    tokenKind['D'] = Char_D;
    tokenKind['E'] = Char_E;
    tokenKind['F'] = Char_F;
    tokenKind['G'] = Char_G;
    tokenKind['H'] = Char_H;
    tokenKind['I'] = Char_I;
    tokenKind['J'] = Char_J;
    tokenKind['K'] = Char_K;
    tokenKind['L'] = Char_L;
    tokenKind['M'] = Char_M;
    tokenKind['N'] = Char_N;
    tokenKind['O'] = Char_O;
    tokenKind['P'] = Char_P;
    tokenKind['Q'] = Char_Q;
    tokenKind['R'] = Char_R;
    tokenKind['S'] = Char_S;
    tokenKind['T'] = Char_T;
    tokenKind['U'] = Char_U;
    tokenKind['V'] = Char_V;
    tokenKind['W'] = Char_W;
    tokenKind['X'] = Char_X;
    tokenKind['Y'] = Char_Y;
    tokenKind['Z'] = Char_Z;

    tokenKind['_'] = Char__;

    tokenKind['0'] = Char_0;
    tokenKind['1'] = Char_1;
    tokenKind['2'] = Char_2;
    tokenKind['3'] = Char_3;
    tokenKind['4'] = Char_4;
    tokenKind['5'] = Char_5;
    tokenKind['6'] = Char_6;
    tokenKind['7'] = Char_7;
    tokenKind['8'] = Char_8;
    tokenKind['9'] = Char_9;

};

final int getKind(char c)
{
    return (c < 128 ? tokenKind[c] : 0);
}


    public SQLKeywordLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  KeyWord ::= A C C E S S
        //
        		    keywordKind[1] = (TK_ACCESS);
        		  
        		
        //
        // Rule 2:  KeyWord ::= A C C T N G
        //
        		    keywordKind[2] = (TK_ACCTNG);
        		  
        		
        //
        // Rule 3:  KeyWord ::= A C T I O N
        //
        		    keywordKind[3] = (TK_ACTION);
        		  
        		
        //
        // Rule 4:  KeyWord ::= A C T I V A T E
        //
        		    keywordKind[4] = (TK_ACTIVATE);
        		  
        		
        //
        // Rule 5:  KeyWord ::= A D D
        //
        		    keywordKind[5] = (TK_ADD);
        		  
        		
        //
        // Rule 6:  KeyWord ::= A D M I N
        //
        		    keywordKind[6] = (TK_ADMIN);
        		  
        		
        //
        // Rule 7:  KeyWord ::= A D M I N I S T R A T I O N
        //
        		    keywordKind[7] = (TK_ADMINISTRATION);
        		  
        		
        //
        // Rule 8:  KeyWord ::= A F T E R
        //
        		    keywordKind[8] = (TK_AFTER);
        		  
        		
        //
        // Rule 9:  KeyWord ::= A G E
        //
        		    keywordKind[9] = (TK_AGE);
        		  
        		
        //
        // Rule 10:  KeyWord ::= A L I A S
        //
        		    keywordKind[10] = (TK_ALIAS);
        		  
        		
        //
        // Rule 11:  KeyWord ::= A L L
        //
        		    keywordKind[11] = (TK_ALL);
        		  
        		
        //
        // Rule 12:  KeyWord ::= A L L O C A T E
        //
        		    keywordKind[12] = (TK_ALLOCATE);
        		  
        		
        //
        // Rule 13:  KeyWord ::= A L L O W
        //
        		    keywordKind[13] = (TK_ALLOW);
        		  
        		
        //
        // Rule 14:  KeyWord ::= A L T E R
        //
        		    keywordKind[14] = (TK_ALTER);
        		  
        		
        //
        // Rule 15:  KeyWord ::= A L T E R I N
        //
        		    keywordKind[15] = (TK_ALTERIN);
        		  
        		
        //
        // Rule 16:  KeyWord ::= A L W A Y S
        //
        		    keywordKind[16] = (TK_ALWAYS);
        		  
        		
        //
        // Rule 17:  KeyWord ::= A N D
        //
        		    keywordKind[17] = (TK_AND);
        		  
        		
        //
        // Rule 18:  KeyWord ::= A N Y
        //
        		    keywordKind[18] = (TK_ANY);
        		  
        		
        //
        // Rule 19:  KeyWord ::= A P P E N D
        //
        		    keywordKind[19] = (TK_APPEND);
        		  
        		
        //
        // Rule 20:  KeyWord ::= A P P L N A M E
        //
        		    keywordKind[20] = (TK_APPLNAME);
        		  
        		
        //
        // Rule 21:  KeyWord ::= A R R A Y
        //
        		    keywordKind[21] = (TK_ARRAY);
        		  
        		
        //
        // Rule 22:  KeyWord ::= A S
        //
        		    keywordKind[22] = (TK_AS);
        		  
        		
        //
        // Rule 23:  KeyWord ::= A S C
        //
        		    keywordKind[23] = (TK_ASC);
        		  
        		
        //
        // Rule 24:  KeyWord ::= A S C I I
        //
        		    keywordKind[24] = (TK_ASCII);
        		  
        		
        //
        // Rule 25:  KeyWord ::= A S E N S I T I V E
        //
        		    keywordKind[25] = (TK_ASENSITIVE);
        		  
        		
        //
        // Rule 26:  KeyWord ::= A S S I G N M E N T
        //
        		    keywordKind[26] = (TK_ASSIGNMENT);
        		  
        		
        //
        // Rule 27:  KeyWord ::= A S S O C I A T E
        //
        		    keywordKind[27] = (TK_ASSOCIATE);
        		  
        		
        //
        // Rule 28:  KeyWord ::= A S T
        //
        		    keywordKind[28] = (TK_AST);
        		  
        		
        //
        // Rule 29:  KeyWord ::= A S U T I M E
        //
        		    keywordKind[29] = (TK_ASUTIME);
        		  
        		
        //
        // Rule 30:  KeyWord ::= A T
        //
        		    keywordKind[30] = (TK_AT);
        		  
        		
        //
        // Rule 31:  KeyWord ::= A T O M I C
        //
        		    keywordKind[31] = (TK_ATOMIC);
        		  
        		
        //
        // Rule 32:  KeyWord ::= A T T R I B U T E S
        //
        		    keywordKind[32] = (TK_ATTRIBUTES);
        		  
        		
        //
        // Rule 33:  KeyWord ::= A U T H O R I Z A T I O N
        //
        		    keywordKind[33] = (TK_AUTHORIZATION);
        		  
        		
        //
        // Rule 34:  KeyWord ::= A U T H I D
        //
        		    keywordKind[34] = (TK_AUTHID);
        		  
        		
        //
        // Rule 35:  KeyWord ::= A U T O M A T I C
        //
        		    keywordKind[35] = (TK_AUTOMATIC);
        		  
        		
        //
        // Rule 36:  KeyWord ::= A U T O S T A R T
        //
        		    keywordKind[36] = (TK_AUTOSTART);
        		  
        		
        //
        // Rule 37:  KeyWord ::= B
        //
        		    keywordKind[37] = (TK_B);
        		  
        		
        //
        // Rule 38:  KeyWord ::= B E F O R E
        //
        		    keywordKind[38] = (TK_BEFORE);
        		  
        		
        //
        // Rule 39:  KeyWord ::= B E G I N
        //
        		    keywordKind[39] = (TK_BEGIN);
        		  
        		
        //
        // Rule 40:  KeyWord ::= B E R N O U L L I
        //
        		    keywordKind[40] = (TK_BERNOULLI);
        		  
        		
        //
        // Rule 41:  KeyWord ::= B E T W E E N
        //
        		    keywordKind[41] = (TK_BETWEEN);
        		  
        		
        //
        // Rule 42:  KeyWord ::= B I G I N T
        //
        		    keywordKind[42] = (TK_BIGINT);
        		  
        		
        //
        // Rule 43:  KeyWord ::= B I N A R Y
        //
        		    keywordKind[43] = (TK_BINARY);
        		  
        		
        //
        // Rule 44:  KeyWord ::= B I N D
        //
        		    keywordKind[44] = (TK_BIND);
        		  
        		
        //
        // Rule 45:  KeyWord ::= B I N D A D D
        //
        		    keywordKind[45] = (TK_BINDADD);
        		  
        		
        //
        // Rule 46:  KeyWord ::= B I T
        //
        		    keywordKind[46] = (TK_BIT);
        		  
        		
        //
        // Rule 47:  KeyWord ::= B L O B
        //
        		    keywordKind[47] = (TK_BLOB);
        		  
        		
        //
        // Rule 48:  KeyWord ::= B L O C K E D
        //
        		    keywordKind[48] = (TK_BLOCKED);
        		  
        		
        //
        // Rule 49:  KeyWord ::= B L O C K S I Z E
        //
        		    keywordKind[49] = (TK_BLOCKSIZE);
        		  
        		
        //
        // Rule 50:  KeyWord ::= B O T H
        //
        		    keywordKind[50] = (TK_BOTH);
        		  
        		
        //
        // Rule 51:  KeyWord ::= B U F F E R
        //
        		    keywordKind[51] = (TK_BUFFER);
        		  
        		
        //
        // Rule 52:  KeyWord ::= B U F F E R P O O L
        //
        		    keywordKind[52] = (TK_BUFFERPOOL);
        		  
        		
        //
        // Rule 53:  KeyWord ::= B U F F E R P O O L S
        //
        		    keywordKind[53] = (TK_BUFFERPOOLS);
        		  
        		
        //
        // Rule 54:  KeyWord ::= B U F F E R S I Z E
        //
        		    keywordKind[54] = (TK_BUFFERSIZE);
        		  
        		
        //
        // Rule 55:  KeyWord ::= B U I L D
        //
        		    keywordKind[55] = (TK_BUILD);
        		  
        		
        //
        // Rule 56:  KeyWord ::= B Y
        //
        		    keywordKind[56] = (TK_BY);
        		  
        		
        //
        // Rule 57:  KeyWord ::= B Y P A S S
        //
        		    keywordKind[57] = (TK_BYPASS);
        		  
        		
        //
        // Rule 58:  KeyWord ::= C
        //
        		    keywordKind[58] = (TK_C);
        		  
        		
        //
        // Rule 59:  KeyWord ::= C A C H E
        //
        		    keywordKind[59] = (TK_CACHE);
        		  
        		
        //
        // Rule 60:  KeyWord ::= C A C H I N G
        //
        		    keywordKind[60] = (TK_CACHING);
        		  
        		
        //
        // Rule 61:  KeyWord ::= C A L L
        //
        		    keywordKind[61] = (TK_CALL);
        		  
        		
        //
        // Rule 62:  KeyWord ::= C A L L E D
        //
        		    keywordKind[62] = (TK_CALLED);
        		  
        		
        //
        // Rule 63:  KeyWord ::= C A L L E R
        //
        		    keywordKind[63] = (TK_CALLER);
        		  
        		
        //
        // Rule 64:  KeyWord ::= C A P T U R E
        //
        		    keywordKind[64] = (TK_CAPTURE);
        		  
        		
        //
        // Rule 65:  KeyWord ::= C A R D I N A L I T I E S
        //
        		    keywordKind[65] = (TK_CARDINALITIES);
        		  
        		
        //
        // Rule 66:  KeyWord ::= C A R D I N A L I T Y
        //
        		    keywordKind[66] = (TK_CARDINALITY);
        		  
        		
        //
        // Rule 67:  KeyWord ::= C A S C A D E
        //
        		    keywordKind[67] = (TK_CASCADE);
        		  
        		
        //
        // Rule 68:  KeyWord ::= C A S C A D E D
        //
        		    keywordKind[68] = (TK_CASCADED);
        		  
        		
        //
        // Rule 69:  KeyWord ::= C A S E
        //
        		    keywordKind[69] = (TK_CASE);
        		  
        		
        //
        // Rule 70:  KeyWord ::= C A S T
        //
        		    keywordKind[70] = (TK_CAST);
        		  
        		
        //
        // Rule 71:  KeyWord ::= C C S I D
        //
        		    keywordKind[71] = (TK_CCSID);
        		  
        		
        //
        // Rule 72:  KeyWord ::= C H A N G E
        //
        		    keywordKind[72] = (TK_CHANGE);
        		  
        		
        //
        // Rule 73:  KeyWord ::= C H A N G E D
        //
        		    keywordKind[73] = (TK_CHANGED);
        		  
        		
        //
        // Rule 74:  KeyWord ::= C H A N G E S
        //
        		    keywordKind[74] = (TK_CHANGES);
        		  
        		
        //
        // Rule 75:  KeyWord ::= C H A R
        //
        		    keywordKind[75] = (TK_CHAR);
        		  
        		
        //
        // Rule 76:  KeyWord ::= C H A R A C T E R
        //
        		    keywordKind[76] = (TK_CHARACTER);
        		  
        		
        //
        // Rule 77:  KeyWord ::= C H E C K
        //
        		    keywordKind[77] = (TK_CHECK);
        		  
        		
        //
        // Rule 78:  KeyWord ::= C H E C K E D
        //
        		    keywordKind[78] = (TK_CHECKED);
        		  
        		
        //
        // Rule 79:  KeyWord ::= C L I E N T
        //
        		    keywordKind[79] = (TK_CLIENT);
        		  
        		
        //
        // Rule 80:  KeyWord ::= C L O B
        //
        		    keywordKind[80] = (TK_CLOB);
        		  
        		
        //
        // Rule 81:  KeyWord ::= C L O S E
        //
        		    keywordKind[81] = (TK_CLOSE);
        		  
        		
        //
        // Rule 82:  KeyWord ::= C L R
        //
        		    keywordKind[82] = (TK_CLR);
        		  
        		
        //
        // Rule 83:  KeyWord ::= C L U S T E R
        //
        		    keywordKind[83] = (TK_CLUSTER);
        		  
        		
        //
        // Rule 84:  KeyWord ::= C O B O L
        //
        		    keywordKind[84] = (TK_COBOL);
        		  
        		
        //
        // Rule 85:  KeyWord ::= C O L L E C T
        //
        		    keywordKind[85] = (TK_COLLECT);
        		  
        		
        //
        // Rule 86:  KeyWord ::= C O L L I D
        //
        		    keywordKind[86] = (TK_COLLID);
        		  
        		
        //
        // Rule 87:  KeyWord ::= C O L U M N
        //
        		    keywordKind[87] = (TK_COLUMN);
        		  
        		
        //
        // Rule 88:  KeyWord ::= C O L U M N S
        //
        		    keywordKind[88] = (TK_COLUMNS);
        		  
        		
        //
        // Rule 89:  KeyWord ::= C O M M
        //
        		    keywordKind[89] = (TK_COMM);
        		  
        		
        //
        // Rule 90:  KeyWord ::= C O M M E N T
        //
        		    keywordKind[90] = (TK_COMMENT);
        		  
        		
        //
        // Rule 91:  KeyWord ::= C O M M I T
        //
        		    keywordKind[91] = (TK_COMMIT);
        		  
        		
        //
        // Rule 92:  KeyWord ::= C O M M I T T E D
        //
        		    keywordKind[92] = (TK_COMMITTED);
        		  
        		
        //
        // Rule 93:  KeyWord ::= C O M P A C T
        //
        		    keywordKind[93] = (TK_COMPACT);
        		  
        		
        //
        // Rule 94:  KeyWord ::= C O M P A R E
        //
        		    keywordKind[94] = (TK_COMPARE);
        		  
        		
        //
        // Rule 95:  KeyWord ::= C O M P A R I S O N S
        //
        		    keywordKind[95] = (TK_COMPARISONS);
        		  
        		
        //
        // Rule 96:  KeyWord ::= C O M P R E S S
        //
        		    keywordKind[96] = (TK_COMPRESS);
        		  
        		
        //
        // Rule 97:  KeyWord ::= C O M P R E S S I O N
        //
        		    keywordKind[97] = (TK_COMPRESSION);
        		  
        		
        //
        // Rule 98:  KeyWord ::= C O N C A T
        //
        		    keywordKind[98] = (TK_CONCAT);
        		  
        		
        //
        // Rule 99:  KeyWord ::= C O N D I T I O N
        //
        		    keywordKind[99] = (TK_CONDITION);
        		  
        		
        //
        // Rule 100:  KeyWord ::= C O N N
        //
        		    keywordKind[100] = (TK_CONN);
        		  
        		
        //
        // Rule 101:  KeyWord ::= C O N N E C T
        //
        		    keywordKind[101] = (TK_CONNECT);
        		  
        		
        //
        // Rule 102:  KeyWord ::= C O N N E C T I O N S
        //
        		    keywordKind[102] = (TK_CONNECTIONS);
        		  
        		
        //
        // Rule 103:  KeyWord ::= C O N N E C T O P T
        //
        		    keywordKind[103] = (TK_CONNECTOPT);
        		  
        		
        //
        // Rule 104:  KeyWord ::= C O N N H E A D E R
        //
        		    keywordKind[104] = (TK_CONNHEADER);
        		  
        		
        //
        // Rule 105:  KeyWord ::= C O N N M E M U S E
        //
        		    keywordKind[105] = (TK_CONNMEMUSE);
        		  
        		
        //
        // Rule 106:  KeyWord ::= C O N S E R V A T I V E
        //
        		    keywordKind[106] = (TK_CONSERVATIVE);
        		  
        		
        //
        // Rule 107:  KeyWord ::= C O N S T R A I N T
        //
        		    keywordKind[107] = (TK_CONSTRAINT);
        		  
        		
        //
        // Rule 108:  KeyWord ::= C O N S T R A I N T S
        //
        		    keywordKind[108] = (TK_CONSTRAINTS);
        		  
        		
        //
        // Rule 109:  KeyWord ::= C O N S T R U C T O R
        //
        		    keywordKind[109] = (TK_CONSTRUCTOR);
        		  
        		
        //
        // Rule 110:  KeyWord ::= C O N T A I N E R S
        //
        		    keywordKind[110] = (TK_CONTAINERS);
        		  
        		
        //
        // Rule 111:  KeyWord ::= C O N T A I N S
        //
        		    keywordKind[111] = (TK_CONTAINS);
        		  
        		
        //
        // Rule 112:  KeyWord ::= C O N T E N T
        //
        		    keywordKind[112] = (TK_CONTENT);
        		  
        		
        //
        // Rule 113:  KeyWord ::= C O N T I N U E
        //
        		    keywordKind[113] = (TK_CONTINUE);
        		  
        		
        //
        // Rule 114:  KeyWord ::= C O N T R O L
        //
        		    keywordKind[114] = (TK_CONTROL);
        		  
        		
        //
        // Rule 115:  KeyWord ::= C O P Y
        //
        		    keywordKind[115] = (TK_COPY);
        		  
        		
        //
        // Rule 116:  KeyWord ::= C O U N T
        //
        		    keywordKind[116] = (TK_COUNT);
        		  
        		
        //
        // Rule 117:  KeyWord ::= C P U
        //
        		    keywordKind[117] = (TK_CPU);
        		  
        		
        //
        // Rule 118:  KeyWord ::= C R E A T E
        //
        		    keywordKind[118] = (TK_CREATE);
        		  
        		
        //
        // Rule 119:  KeyWord ::= C R E A T E I N
        //
        		    keywordKind[119] = (TK_CREATEIN);
        		  
        		
        //
        // Rule 120:  KeyWord ::= C R E A T E T A B
        //
        		    keywordKind[120] = (TK_CREATETAB);
        		  
        		
        //
        // Rule 121:  KeyWord ::= C S
        //
        		    keywordKind[121] = (TK_CS);
        		  
        		
        //
        // Rule 122:  KeyWord ::= C U B E
        //
        		    keywordKind[122] = (TK_CUBE);
        		  
        		
        //
        // Rule 123:  KeyWord ::= C U R R E N T
        //
        		    keywordKind[123] = (TK_CURRENT);
        		  
        		
        //
        // Rule 124:  KeyWord ::= C U R R E N T _ D A T E
        //
                   keywordKind[124] = (TK_CURRENT_DATE);
                  
                
        //
        // Rule 125:  KeyWord ::= C U R R E N T _ D E F A U L T _ T R A N S F O R M _ G R O U P
        //
                   keywordKind[125] = (TK_CURRENT_DEFAULT_TRANSFORM_GROUP);
                  
                
        //
        // Rule 126:  KeyWord ::= C U R R E N T _ P A T H
        //
                   keywordKind[126] = (TK_CURRENT_PATH);
                  
                
        //
        // Rule 127:  KeyWord ::= C U R R E N T _ R O L E
        //
                   keywordKind[127] = (TK_CURRENT_ROLE);
                  
                
        //
        // Rule 128:  KeyWord ::= C U R R E N T _ T I M E
        //
                   keywordKind[128] = (TK_CURRENT_TIME);
                  
                
        //
        // Rule 129:  KeyWord ::= C U R R E N T _ T I M E S T A M P
        //
                   keywordKind[129] = (TK_CURRENT_TIMESTAMP);
                  
                
        //
        // Rule 130:  KeyWord ::= C U R R E N T _ T R A N S F O R M _ G R O U P _ F O R _ T Y P E
        //
                   keywordKind[130] = (TK_CURRENT_TRANSFORM_GROUP_FOR_TYPE);
                  
                
        //
        // Rule 131:  KeyWord ::= C U R R E N T _ U S E R
        //
                   keywordKind[131] = (TK_CURRENT_USER);
                  
                
        //
        // Rule 132:  KeyWord ::= C U R R V A L
        //
        		    keywordKind[132] = (TK_CURRVAL);
        		  
        		
        //
        // Rule 133:  KeyWord ::= C U R S O R
        //
        		    keywordKind[133] = (TK_CURSOR);
        		  
        		
        //
        // Rule 134:  KeyWord ::= C U R S O R S
        //
        		    keywordKind[134] = (TK_CURSORS);
        		  
        		
        //
        // Rule 135:  KeyWord ::= C Y C L E
        //
        		    keywordKind[135] = (TK_CYCLE);
        		  
        		
        //
        // Rule 136:  KeyWord ::= D A T A
        //
        		    keywordKind[136] = (TK_DATA);
        		  
        		
        //
        // Rule 137:  KeyWord ::= D A T A B A S E
        //
        		    keywordKind[137] = (TK_DATABASE);
        		  
        		
        //
        // Rule 138:  KeyWord ::= D A T A L I N K
        //
        		    keywordKind[138] = (TK_DATALINK);
        		  
        		
        //
        // Rule 139:  KeyWord ::= D A T E
        //
        		    keywordKind[139] = (TK_DATE);
        		  
        		
        //
        // Rule 140:  KeyWord ::= D A Y
        //
        		    keywordKind[140] = (TK_DAY);
        		  
        		
        //
        // Rule 141:  KeyWord ::= D A Y S
        //
        		    keywordKind[141] = (TK_DAYS);
        		  
        		
        //
        // Rule 142:  KeyWord ::= D B
        //
        		    keywordKind[142] = (TK_DB);
        		  
        		
        //
        // Rule 143:  KeyWord ::= D B A D M
        //
        		    keywordKind[143] = (TK_DBADM);
        		  
        		
        //
        // Rule 144:  KeyWord ::= D B C L O B
        //
        		    keywordKind[144] = (TK_DBCLOB);
        		  
        		
        //
        // Rule 145:  KeyWord ::= D B I N F O
        //
        		    keywordKind[145] = (TK_DBINFO);
        		  
        		
        //
        // Rule 146:  KeyWord ::= D B M E M U S E
        //
        		    keywordKind[146] = (TK_DBMEMUSE);
        		  
        		
        //
        // Rule 147:  KeyWord ::= D B P A R T I T I O N N U M
        //
        		    keywordKind[147] = (TK_DBPARTITIONNUM);
        		  
        		
        //
        // Rule 148:  KeyWord ::= D B P A R T I T I O N N U M S
        //
        		    keywordKind[148] = (TK_DBPARTITIONNUMS);
        		  
        		
        //
        // Rule 149:  KeyWord ::= D B 2 D A R I
        //
        		    keywordKind[149] = (TK_DB2DARI);
        		  
        		
        //
        // Rule 150:  KeyWord ::= D B 2 G E N R L
        //
        		    keywordKind[150] = (TK_DB2GENRL);
        		  
        		
        //
        // Rule 151:  KeyWord ::= D B 2 G E N E R A L
        //
        		    keywordKind[151] = (TK_DB2GENERAL);
        		  
        		
        //
        // Rule 152:  KeyWord ::= D B 2 O P T I O N S
        //
        		    keywordKind[152] = (TK_DB2OPTIONS);
        		  
        		
        //
        // Rule 153:  KeyWord ::= D B 2 S P L
        //
        		    keywordKind[153] = (TK_DB2SPL);
        		  
        		
        //
        // Rule 154:  KeyWord ::= D B 2 S Q L
        //
        		    keywordKind[154] = (TK_DB2SQL);
        		  
        		
        //
        // Rule 155:  KeyWord ::= D E A C T I V A T E
        //
        		    keywordKind[155] = (TK_DEACTIVATE);
        		  
        		
        //
        // Rule 156:  KeyWord ::= D E A D L O C K
        //
        		    keywordKind[156] = (TK_DEADLOCK);
        		  
        		
        //
        // Rule 157:  KeyWord ::= D E A D L O C K S
        //
        		    keywordKind[157] = (TK_DEADLOCKS);
        		  
        		
        //
        // Rule 158:  KeyWord ::= D E C
        //
        		    keywordKind[158] = (TK_DEC);
        		  
        		
        //
        // Rule 159:  KeyWord ::= D E C I M A L
        //
        		    keywordKind[159] = (TK_DECIMAL);
        		  
        		
        //
        // Rule 160:  KeyWord ::= D E C L A R E
        //
        		    keywordKind[160] = (TK_DECLARE);
        		  
        		
        //
        // Rule 161:  KeyWord ::= D E F A U L T
        //
        		    keywordKind[161] = (TK_DEFAULT);
        		  
        		
        //
        // Rule 162:  KeyWord ::= D E F A U L T S
        //
        		    keywordKind[162] = (TK_DEFAULTS);
        		  
        		
        //
        // Rule 163:  KeyWord ::= D E F I N E
        //
        		    keywordKind[163] = (TK_DEFINE);
        		  
        		
        //
        // Rule 164:  KeyWord ::= D E F I N I T I O N
        //
        		    keywordKind[164] = (TK_DEFINITION);
        		  
        		
        //
        // Rule 165:  KeyWord ::= D E G R E E
        //
        		    keywordKind[165] = (TK_DEGREE);
        		  
        		
        //
        // Rule 166:  KeyWord ::= D E F E R
        //
        		    keywordKind[166] = (TK_DEFER);
        		  
        		
        //
        // Rule 167:  KeyWord ::= D E F E R R E D
        //
        		    keywordKind[167] = (TK_DEFERRED);
        		  
        		
        //
        // Rule 168:  KeyWord ::= D E L E T E
        //
        		    keywordKind[168] = (TK_DELETE);
        		  
        		
        //
        // Rule 169:  KeyWord ::= D E S C
        //
        		    keywordKind[169] = (TK_DESC);
        		  
        		
        //
        // Rule 170:  KeyWord ::= D E S C R I P T O R
        //
        		    keywordKind[170] = (TK_DESCRIPTOR);
        		  
        		
        //
        // Rule 171:  KeyWord ::= D E T A I L E D
        //
        		    keywordKind[171] = (TK_DETAILED);
        		  
        		
        //
        // Rule 172:  KeyWord ::= D E T A I L S
        //
        		    keywordKind[172] = (TK_DETAILS);
        		  
        		
        //
        // Rule 173:  KeyWord ::= D E T E R M I N E D
        //
        		    keywordKind[173] = (TK_DETERMINED);
        		  
        		
        //
        // Rule 174:  KeyWord ::= D E T E R M I N I S T I C
        //
        		    keywordKind[174] = (TK_DETERMINISTIC);
        		  
        		
        //
        // Rule 175:  KeyWord ::= D E V I C E
        //
        		    keywordKind[175] = (TK_DEVICE);
        		  
        		
        //
        // Rule 176:  KeyWord ::= D I A G N O S T I C S
        //
        		    keywordKind[176] = (TK_DIAGNOSTICS);
        		  
        		
        //
        // Rule 177:  KeyWord ::= D I M E N S I O N S
        //
        		    keywordKind[177] = (TK_DIMENSIONS);
        		  
        		
        //
        // Rule 178:  KeyWord ::= D I R T Y
        //
        		    keywordKind[178] = (TK_DIRTY);
        		  
        		
        //
        // Rule 179:  KeyWord ::= D I S A L L O W
        //
        		    keywordKind[179] = (TK_DISALLOW);
        		  
        		
        //
        // Rule 180:  KeyWord ::= D I S P A T C H
        //
        		    keywordKind[180] = (TK_DISPATCH);
        		  
        		
        //
        // Rule 181:  KeyWord ::= D I S T I N C T
        //
        		    keywordKind[181] = (TK_DISTINCT);
        		  
        		
        //
        // Rule 182:  KeyWord ::= D I S T R I B U T I O N
        //
        		    keywordKind[182] = (TK_DISTRIBUTION);
        		  
        		
        //
        // Rule 183:  KeyWord ::= D L C O N N
        //
        		    keywordKind[183] = (TK_DLCONN);
        		  
        		
        //
        // Rule 184:  KeyWord ::= D L L O C K
        //
        		    keywordKind[184] = (TK_DLLOCK);
        		  
        		
        //
        // Rule 185:  KeyWord ::= D O
        //
        		    keywordKind[185] = (TK_DO);
        		  
        		
        //
        // Rule 186:  KeyWord ::= D O U B L E
        //
        		    keywordKind[186] = (TK_DOUBLE);
        		  
        		
        //
        // Rule 187:  KeyWord ::= D R O P
        //
        		    keywordKind[187] = (TK_DROP);
        		  
        		
        //
        // Rule 188:  KeyWord ::= D R O P I N
        //
        		    keywordKind[188] = (TK_DROPIN);
        		  
        		
        //
        // Rule 189:  KeyWord ::= D R O P P E D
        //
        		    keywordKind[189] = (TK_DROPPED);
        		  
        		
        //
        // Rule 190:  KeyWord ::= E A C H
        //
        		    keywordKind[190] = (TK_EACH);
        		  
        		
        //
        // Rule 191:  KeyWord ::= E L E M E N T
        //
        		    keywordKind[191] = (TK_ELEMENT);
        		  
        		
        //
        // Rule 192:  KeyWord ::= E L S E
        //
        		    keywordKind[192] = (TK_ELSE);
        		  
        		
        //
        // Rule 193:  KeyWord ::= E L S E I F
        //
        		    keywordKind[193] = (TK_ELSEIF);
        		  
        		
        //
        // Rule 194:  KeyWord ::= E M P T Y
        //
        		    keywordKind[194] = (TK_EMPTY);
        		  
        		
        //
        // Rule 195:  KeyWord ::= E N C R Y P T I O N
        //
        		    keywordKind[195] = (TK_ENCRYPTION);
        		  
        		
        //
        // Rule 196:  KeyWord ::= E N D
        //
        		    keywordKind[196] = (TK_END);
        		  
        		
        //
        // Rule 197:  KeyWord ::= E N D I N G
        //
        		    keywordKind[197] = (TK_ENDING);
        		  
        		
        //
        // Rule 198:  KeyWord ::= E N F O R C E D
        //
        		    keywordKind[198] = (TK_ENFORCED);
        		  
        		
        //
        // Rule 199:  KeyWord ::= E R A S E
        //
        		    keywordKind[199] = (TK_ERASE);
        		  
        		
        //
        // Rule 200:  KeyWord ::= E S C A P E
        //
        		    keywordKind[200] = (TK_ESCAPE);
        		  
        		
        //
        // Rule 201:  KeyWord ::= E S T I M A T E
        //
        		    keywordKind[201] = (TK_ESTIMATE);
        		  
        		
        //
        // Rule 202:  KeyWord ::= E U R
        //
        		    keywordKind[202] = (TK_EUR);
        		  
        		
        //
        // Rule 203:  KeyWord ::= E V A L U A T E
        //
        		    keywordKind[203] = (TK_EVALUATE);
        		  
        		
        //
        // Rule 204:  KeyWord ::= E V E N T
        //
        		    keywordKind[204] = (TK_EVENT);
        		  
        		
        //
        // Rule 205:  KeyWord ::= E X A C T
        //
        		    keywordKind[205] = (TK_EXACT);
        		  
        		
        //
        // Rule 206:  KeyWord ::= E X C E P T
        //
        		    keywordKind[206] = (TK_EXCEPT);
        		  
        		
        //
        // Rule 207:  KeyWord ::= E X C E P T I O N
        //
        		    keywordKind[207] = (TK_EXCEPTION);
        		  
        		
        //
        // Rule 208:  KeyWord ::= E X C L U D E
        //
        		    keywordKind[208] = (TK_EXCLUDE);
        		  
        		
        //
        // Rule 209:  KeyWord ::= E X C L U D E S
        //
        		    keywordKind[209] = (TK_EXCLUDES);
        		  
        		
        //
        // Rule 210:  KeyWord ::= E X C L U D I N G
        //
        		    keywordKind[210] = (TK_EXCLUDING);
        		  
        		
        //
        // Rule 211:  KeyWord ::= E X C L U S I V E
        //
        		    keywordKind[211] = (TK_EXCLUSIVE);
        		  
        		
        //
        // Rule 212:  KeyWord ::= E X E C N O D E
        //
        		    keywordKind[212] = (TK_EXECNODE);
        		  
        		
        //
        // Rule 213:  KeyWord ::= E X E C U T E
        //
        		    keywordKind[213] = (TK_EXECUTE);
        		  
        		
        //
        // Rule 214:  KeyWord ::= E X I S T S
        //
        		    keywordKind[214] = (TK_EXISTS);
        		  
        		
        //
        // Rule 215:  KeyWord ::= E X I T
        //
        		    keywordKind[215] = (TK_EXIT);
        		  
        		
        //
        // Rule 216:  KeyWord ::= E X P A N D
        //
        		    keywordKind[216] = (TK_EXPAND);
        		  
        		
        //
        // Rule 217:  KeyWord ::= E X P L A I N
        //
        		    keywordKind[217] = (TK_EXPLAIN);
        		  
        		
        //
        // Rule 218:  KeyWord ::= E X P R E S S I O N
        //
        		    keywordKind[218] = (TK_EXPRESSION);
        		  
        		
        //
        // Rule 219:  KeyWord ::= E X T E N D E D
        //
        		    keywordKind[219] = (TK_EXTENDED);
        		  
        		
        //
        // Rule 220:  KeyWord ::= E X T E N S I O N
        //
        		    keywordKind[220] = (TK_EXTENSION);
        		  
        		
        //
        // Rule 221:  KeyWord ::= E X T E N T S I Z E
        //
        		    keywordKind[221] = (TK_EXTENTSIZE);
        		  
        		
        //
        // Rule 222:  KeyWord ::= E X T E R N A L
        //
        		    keywordKind[222] = (TK_EXTERNAL);
        		  
        		
        //
        // Rule 223:  KeyWord ::= F A L S E
        //
        		    keywordKind[223] = (TK_FALSE);
        		  
        		
        //
        // Rule 224:  KeyWord ::= F E D E R A T E D
        //
        		    keywordKind[224] = (TK_FEDERATED);
        		  
        		
        //
        // Rule 225:  KeyWord ::= F E N C E D
        //
        		    keywordKind[225] = (TK_FENCED);
        		  
        		
        //
        // Rule 226:  KeyWord ::= F E T C H
        //
        		    keywordKind[226] = (TK_FETCH);
        		  
        		
        //
        // Rule 227:  KeyWord ::= F I L E
        //
        		    keywordKind[227] = (TK_FILE);
        		  
        		
        //
        // Rule 228:  KeyWord ::= F I L T E R
        //
        		    keywordKind[228] = (TK_FILTER);
        		  
        		
        //
        // Rule 229:  KeyWord ::= F I N A L
        //
        		    keywordKind[229] = (TK_FINAL);
        		  
        		
        //
        // Rule 230:  KeyWord ::= F I R S T
        //
        		    keywordKind[230] = (TK_FIRST);
        		  
        		
        //
        // Rule 231:  KeyWord ::= F L O A T
        //
        		    keywordKind[231] = (TK_FLOAT);
        		  
        		
        //
        // Rule 232:  KeyWord ::= F L U S H
        //
        		    keywordKind[232] = (TK_FLUSH);
        		  
        		
        //
        // Rule 233:  KeyWord ::= F O L L O W I N G
        //
        		    keywordKind[233] = (TK_FOLLOWING);
        		  
        		
        //
        // Rule 234:  KeyWord ::= F O R
        //
        		    keywordKind[234] = (TK_FOR);
        		  
        		
        //
        // Rule 235:  KeyWord ::= F O R C E
        //
        		    keywordKind[235] = (TK_FORCE);
        		  
        		
        //
        // Rule 236:  KeyWord ::= F O R E I G N
        //
        		    keywordKind[236] = (TK_FOREIGN);
        		  
        		
        //
        // Rule 237:  KeyWord ::= F O U N D
        //
        		    keywordKind[237] = (TK_FOUND);
        		  
        		
        //
        // Rule 238:  KeyWord ::= F R E E
        //
        		    keywordKind[238] = (TK_FREE);
        		  
        		
        //
        // Rule 239:  KeyWord ::= F R E E P A G E
        //
        		    keywordKind[239] = (TK_FREEPAGE);
        		  
        		
        //
        // Rule 240:  KeyWord ::= F R O M
        //
        		    keywordKind[240] = (TK_FROM);
        		  
        		
        //
        // Rule 241:  KeyWord ::= F S
        //
        		    keywordKind[241] = (TK_FS);
        		  
        		
        //
        // Rule 242:  KeyWord ::= F U L L
        //
        		    keywordKind[242] = (TK_FULL);
        		  
        		
        //
        // Rule 243:  KeyWord ::= F U N C T I O N
        //
        		    keywordKind[243] = (TK_FUNCTION);
        		  
        		
        //
        // Rule 244:  KeyWord ::= G
        //
        		    keywordKind[244] = (TK_G);
        		  
        		
        //
        // Rule 245:  KeyWord ::= G B P C A C H E
        //
        		    keywordKind[245] = (TK_GBPCACHE);
        		  
        		
        //
        // Rule 246:  KeyWord ::= G E N E R A L
        //
        		    keywordKind[246] = (TK_GENERAL);
        		  
        		
        //
        // Rule 247:  KeyWord ::= G E N E R A T E
        //
        		    keywordKind[247] = (TK_GENERATE);
        		  
        		
        //
        // Rule 248:  KeyWord ::= G E T
        //
        		    keywordKind[248] = (TK_GET);
        		  
        		
        //
        // Rule 249:  KeyWord ::= G L O B A L
        //
        		    keywordKind[249] = (TK_GLOBAL);
        		  
        		
        //
        // Rule 250:  KeyWord ::= G O T O
        //
        		    keywordKind[250] = (TK_GOTO);
        		  
        		
        //
        // Rule 251:  KeyWord ::= G R A N T
        //
        		    keywordKind[251] = (TK_GRANT);
        		  
        		
        //
        // Rule 252:  KeyWord ::= G R A P H I C
        //
        		    keywordKind[252] = (TK_GRAPHIC);
        		  
        		
        //
        // Rule 253:  KeyWord ::= G R O U P
        //
        		    keywordKind[253] = (TK_GROUP);
        		  
        		
        //
        // Rule 254:  KeyWord ::= G R O U P I N G
        //
        		    keywordKind[254] = (TK_GROUPING);
        		  
        		
        //
        // Rule 255:  KeyWord ::= H A N D L E R
        //
        		    keywordKind[255] = (TK_HANDLER);
        		  
        		
        //
        // Rule 256:  KeyWord ::= H A S H I N G
        //
        		    keywordKind[256] = (TK_HASHING);
        		  
        		
        //
        // Rule 257:  KeyWord ::= H A V I N G
        //
        		    keywordKind[257] = (TK_HAVING);
        		  
        		
        //
        // Rule 258:  KeyWord ::= H I D D E N
        //
        		    keywordKind[258] = (TK_HIDDEN);
        		  
        		
        //
        // Rule 259:  KeyWord ::= H I E R A R C H Y
        //
        		    keywordKind[259] = (TK_HIERARCHY);
        		  
        		
        //
        // Rule 260:  KeyWord ::= H I G H
        //
        		    keywordKind[260] = (TK_HIGH);
        		  
        		
        //
        // Rule 261:  KeyWord ::= H O L D
        //
        		    keywordKind[261] = (TK_HOLD);
        		  
        		
        //
        // Rule 262:  KeyWord ::= H O U R
        //
        		    keywordKind[262] = (TK_HOUR);
        		  
        		
        //
        // Rule 263:  KeyWord ::= H O U R S
        //
        		    keywordKind[263] = (TK_HOURS);
        		  
        		
        //
        // Rule 264:  KeyWord ::= H P J
        //
        		    keywordKind[264] = (TK_HPJ);
        		  
        		
        //
        // Rule 265:  KeyWord ::= I D
        //
        		    keywordKind[265] = (TK_ID);
        		  
        		
        //
        // Rule 266:  KeyWord ::= I D E N T I T Y
        //
        		    keywordKind[266] = (TK_IDENTITY);
        		  
        		
        //
        // Rule 267:  KeyWord ::= I F
        //
        		    keywordKind[267] = (TK_IF);
        		  
        		
        //
        // Rule 268:  KeyWord ::= I G N O R E
        //
        		    keywordKind[268] = (TK_IGNORE);
        		  
        		
        //
        // Rule 269:  KeyWord ::= I M M E D I A T E
        //
        		    keywordKind[269] = (TK_IMMEDIATE);
        		  
        		
        //
        // Rule 270:  KeyWord ::= I N
        //
        		    keywordKind[270] = (TK_IN);
        		  
        		
        //
        // Rule 271:  KeyWord ::= I N C L U D E
        //
        		    keywordKind[271] = (TK_INCLUDE);
        		  
        		
        //
        // Rule 272:  KeyWord ::= I N C L U D E S
        //
        		    keywordKind[272] = (TK_INCLUDES);
        		  
        		
        //
        // Rule 273:  KeyWord ::= I N C L U D I N G
        //
        		    keywordKind[273] = (TK_INCLUDING);
        		  
        		
        //
        // Rule 274:  KeyWord ::= I N C R E M E N T
        //
        		    keywordKind[274] = (TK_INCREMENT);
        		  
        		
        //
        // Rule 275:  KeyWord ::= I N C R E M E N T A L
        //
        		    keywordKind[275] = (TK_INCREMENTAL);
        		  
        		
        //
        // Rule 276:  KeyWord ::= I N D E X
        //
        		    keywordKind[276] = (TK_INDEX);
        		  
        		
        //
        // Rule 277:  KeyWord ::= I N D E X E S
        //
        		    keywordKind[277] = (TK_INDEXES);
        		  
        		
        //
        // Rule 278:  KeyWord ::= I N D I C A T O R
        //
        		    keywordKind[278] = (TK_INDICATOR);
        		  
        		
        //
        // Rule 279:  KeyWord ::= I N F I X
        //
        		    keywordKind[279] = (TK_INFIX);
        		  
        		
        //
        // Rule 280:  KeyWord ::= I N F O
        //
        		    keywordKind[280] = (TK_INFO);
        		  
        		
        //
        // Rule 281:  KeyWord ::= I N H E R I T
        //
        		    keywordKind[281] = (TK_INHERIT);
        		  
        		
        //
        // Rule 282:  KeyWord ::= I N I T I A L L Y
        //
        		    keywordKind[282] = (TK_INITIALLY);
        		  
        		
        //
        // Rule 283:  KeyWord ::= I N L I N E
        //
        		    keywordKind[283] = (TK_INLINE);
        		  
        		
        //
        // Rule 284:  KeyWord ::= I N N E R
        //
        		    keywordKind[284] = (TK_INNER);
        		  
        		
        //
        // Rule 285:  KeyWord ::= I N O U T
        //
        		    keywordKind[285] = (TK_INOUT);
        		  
        		
        //
        // Rule 286:  KeyWord ::= I N P U T
        //
        		    keywordKind[286] = (TK_INPUT);
        		  
        		
        //
        // Rule 287:  KeyWord ::= I N S E N S I T I V E
        //
        		    keywordKind[287] = (TK_INSENSITIVE);
        		  
        		
        //
        // Rule 288:  KeyWord ::= I N S E R T
        //
        		    keywordKind[288] = (TK_INSERT);
        		  
        		
        //
        // Rule 289:  KeyWord ::= I N S T E A D
        //
        		    keywordKind[289] = (TK_INSTEAD);
        		  
        		
        //
        // Rule 290:  KeyWord ::= I N T
        //
        		    keywordKind[290] = (TK_INT);
        		  
        		
        //
        // Rule 291:  KeyWord ::= I N T E G E R
        //
        		    keywordKind[291] = (TK_INTEGER);
        		  
        		
        //
        // Rule 292:  KeyWord ::= I N T E G R I T Y
        //
        		    keywordKind[292] = (TK_INTEGRITY);
        		  
        		
        //
        // Rule 293:  KeyWord ::= I N T E R S E C T
        //
        		    keywordKind[293] = (TK_INTERSECT);
        		  
        		
        //
        // Rule 294:  KeyWord ::= I N T O
        //
        		    keywordKind[294] = (TK_INTO);
        		  
        		
        //
        // Rule 295:  KeyWord ::= I O
        //
        		    keywordKind[295] = (TK_IO);
        		  
        		
        //
        // Rule 296:  KeyWord ::= I S
        //
        		    keywordKind[296] = (TK_IS);
        		  
        		
        //
        // Rule 297:  KeyWord ::= I S O
        //
        		    keywordKind[297] = (TK_ISO);
        		  
        		
        //
        // Rule 298:  KeyWord ::= I T E R A T E
        //
        		    keywordKind[298] = (TK_ITERATE);
        		  
        		
        //
        // Rule 299:  KeyWord ::= I S O L A T I O N
        //
        		    keywordKind[299] = (TK_ISOLATION);
        		  
        		
        //
        // Rule 300:  KeyWord ::= J A V A
        //
        		    keywordKind[300] = (TK_JAVA);
        		  
        		
        //
        // Rule 301:  KeyWord ::= J I S
        //
        		    keywordKind[301] = (TK_JIS);
        		  
        		
        //
        // Rule 302:  KeyWord ::= J O I N
        //
        		    keywordKind[302] = (TK_JOIN);
        		  
        		
        //
        // Rule 303:  KeyWord ::= K
        //
        		    keywordKind[303] = (TK_K);
        		  
        		
        //
        // Rule 304:  KeyWord ::= K E E P
        //
        		    keywordKind[304] = (TK_KEEP);
        		  
        		
        //
        // Rule 305:  KeyWord ::= K E Y
        //
        		    keywordKind[305] = (TK_KEY);
        		  
        		
        //
        // Rule 306:  KeyWord ::= K E Y S
        //
        		    keywordKind[306] = (TK_KEYS);
        		  
        		
        //
        // Rule 307:  KeyWord ::= L
        //
        		    keywordKind[307] = (TK_L);
        		  
        		
        //
        // Rule 308:  KeyWord ::= L A N G U A G E
        //
        		    keywordKind[308] = (TK_LANGUAGE);
        		  
        		
        //
        // Rule 309:  KeyWord ::= L A R G E
        //
        		    keywordKind[309] = (TK_LARGE);
        		  
        		
        //
        // Rule 310:  KeyWord ::= L A S T
        //
        		    keywordKind[310] = (TK_LAST);
        		  
        		
        //
        // Rule 311:  KeyWord ::= L A T E R A L
        //
        		    keywordKind[311] = (TK_LATERAL);
        		  
        		
        //
        // Rule 312:  KeyWord ::= L E A D I N G
        //
        		    keywordKind[312] = (TK_LEADING);
        		  
        		
        //
        // Rule 313:  KeyWord ::= L E A V E
        //
        		    keywordKind[313] = (TK_LEAVE);
        		  
        		
        //
        // Rule 314:  KeyWord ::= L E F T
        //
        		    keywordKind[314] = (TK_LEFT);
        		  
        		
        //
        // Rule 315:  KeyWord ::= L E N G T H
        //
        		    keywordKind[315] = (TK_LENGTH);
        		  
        		
        //
        // Rule 316:  KeyWord ::= L E V E L
        //
        		    keywordKind[316] = (TK_LEVEL);
        		  
        		
        //
        // Rule 317:  KeyWord ::= L E V E L 2
        //
        		    keywordKind[317] = (TK_LEVEL2);
        		  
        		
        //
        // Rule 318:  KeyWord ::= L I B R A R Y
        //
        		    keywordKind[318] = (TK_LIBRARY);
        		  
        		
        //
        // Rule 319:  KeyWord ::= L I B R A R Y A D M
        //
        		    keywordKind[319] = (TK_LIBRARYADM);
        		  
        		
        //
        // Rule 320:  KeyWord ::= L I K E
        //
        		    keywordKind[320] = (TK_LIKE);
        		  
        		
        //
        // Rule 321:  KeyWord ::= L I M I T
        //
        		    keywordKind[321] = (TK_LIMIT);
        		  
        		
        //
        // Rule 322:  KeyWord ::= L I N K
        //
        		    keywordKind[322] = (TK_LINK);
        		  
        		
        //
        // Rule 323:  KeyWord ::= L I N K T Y P E
        //
        		    keywordKind[323] = (TK_LINKTYPE);
        		  
        		
        //
        // Rule 324:  KeyWord ::= L O A D
        //
        		    keywordKind[324] = (TK_LOAD);
        		  
        		
        //
        // Rule 325:  KeyWord ::= L O C A L
        //
        		    keywordKind[325] = (TK_LOCAL);
        		  
        		
        //
        // Rule 326:  KeyWord ::= L O C A L T I M E
        //
                   keywordKind[326] = (TK_LOCALTIME);
                  
                
        //
        // Rule 327:  KeyWord ::= L O C A L T I M E S T A M P
        //
                   keywordKind[327] = (TK_LOCALTIMESTAMP);
                  
                
        //
        // Rule 328:  KeyWord ::= L O C A L E
        //
        		    keywordKind[328] = (TK_LOCALE);
        		  
        		
        //
        // Rule 329:  KeyWord ::= L O C A T O R
        //
        		    keywordKind[329] = (TK_LOCATOR);
        		  
        		
        //
        // Rule 330:  KeyWord ::= L O C A T O R S
        //
        		    keywordKind[330] = (TK_LOCATORS);
        		  
        		
        //
        // Rule 331:  KeyWord ::= L O C K
        //
        		    keywordKind[331] = (TK_LOCK);
        		  
        		
        //
        // Rule 332:  KeyWord ::= L O C K S
        //
        		    keywordKind[332] = (TK_LOCKS);
        		  
        		
        //
        // Rule 333:  KeyWord ::= L O C K S I Z E
        //
        		    keywordKind[333] = (TK_LOCKSIZE);
        		  
        		
        //
        // Rule 334:  KeyWord ::= L O G
        //
        		    keywordKind[334] = (TK_LOG);
        		  
        		
        //
        // Rule 335:  KeyWord ::= L O G G E D
        //
        		    keywordKind[335] = (TK_LOGGED);
        		  
        		
        //
        // Rule 336:  KeyWord ::= L O N G
        //
        		    keywordKind[336] = (TK_LONG);
        		  
        		
        //
        // Rule 337:  KeyWord ::= L O N G V A R
        //
        		    keywordKind[337] = (TK_LONGVAR);
        		  
        		
        //
        // Rule 338:  KeyWord ::= L O O P
        //
        		    keywordKind[338] = (TK_LOOP);
        		  
        		
        //
        // Rule 339:  KeyWord ::= L O W
        //
        		    keywordKind[339] = (TK_LOW);
        		  
        		
        //
        // Rule 340:  KeyWord ::= M
        //
        		    keywordKind[340] = (TK_M);
        		  
        		
        //
        // Rule 341:  KeyWord ::= M A N A G E D
        //
        		    keywordKind[341] = (TK_MANAGED);
        		  
        		
        //
        // Rule 342:  KeyWord ::= M A N U A L S T A R T
        //
        		    keywordKind[342] = (TK_MANUALSTART);
        		  
        		
        //
        // Rule 343:  KeyWord ::= M A P P I N G
        //
        		    keywordKind[343] = (TK_MAPPING);
        		  
        		
        //
        // Rule 344:  KeyWord ::= M A T C H E D
        //
        		    keywordKind[344] = (TK_MATCHED);
        		  
        		
        //
        // Rule 345:  KeyWord ::= M A T E R I A L I Z E D
        //
        		    keywordKind[345] = (TK_MATERIALIZED);
        		  
        		
        //
        // Rule 346:  KeyWord ::= M A X F I L E S
        //
        		    keywordKind[346] = (TK_MAXFILES);
        		  
        		
        //
        // Rule 347:  KeyWord ::= M A X F I L E S I Z E
        //
        		    keywordKind[347] = (TK_MAXFILESIZE);
        		  
        		
        //
        // Rule 348:  KeyWord ::= M A X V A L U E
        //
        		    keywordKind[348] = (TK_MAXVALUE);
        		  
        		
        //
        // Rule 349:  KeyWord ::= M D C
        //
        		    keywordKind[349] = (TK_MDC);
        		  
        		
        //
        // Rule 350:  KeyWord ::= M E R G E
        //
        		    keywordKind[350] = (TK_MERGE);
        		  
        		
        //
        // Rule 351:  KeyWord ::= M E T H O D
        //
        		    keywordKind[351] = (TK_METHOD);
        		  
        		
        //
        // Rule 352:  KeyWord ::= M E T H O D S
        //
        		    keywordKind[352] = (TK_METHODS);
        		  
        		
        //
        // Rule 353:  KeyWord ::= M I C R O S E C O N D
        //
        		    keywordKind[353] = (TK_MICROSECOND);
        		  
        		
        //
        // Rule 354:  KeyWord ::= M I C R O S E C O N D S
        //
        		    keywordKind[354] = (TK_MICROSECONDS);
        		  
        		
        //
        // Rule 355:  KeyWord ::= M I N P C T U S E D
        //
        		    keywordKind[355] = (TK_MINPCTUSED);
        		  
        		
        //
        // Rule 356:  KeyWord ::= M I N U T E
        //
        		    keywordKind[356] = (TK_MINUTE);
        		  
        		
        //
        // Rule 357:  KeyWord ::= M I N U T E S
        //
        		    keywordKind[357] = (TK_MINUTES);
        		  
        		
        //
        // Rule 358:  KeyWord ::= M I N V A L U E
        //
        		    keywordKind[358] = (TK_MINVALUE);
        		  
        		
        //
        // Rule 359:  KeyWord ::= M I X E D
        //
        		    keywordKind[359] = (TK_MIXED);
        		  
        		
        //
        // Rule 360:  KeyWord ::= M O D E
        //
        		    keywordKind[360] = (TK_MODE);
        		  
        		
        //
        // Rule 361:  KeyWord ::= M O D I F I E S
        //
        		    keywordKind[361] = (TK_MODIFIES);
        		  
        		
        //
        // Rule 362:  KeyWord ::= M O N I T O R
        //
        		    keywordKind[362] = (TK_MONITOR);
        		  
        		
        //
        // Rule 363:  KeyWord ::= M O N T H
        //
        		    keywordKind[363] = (TK_MONTH);
        		  
        		
        //
        // Rule 364:  KeyWord ::= M O N T H S
        //
        		    keywordKind[364] = (TK_MONTHS);
        		  
        		
        //
        // Rule 365:  KeyWord ::= M O V E M E N T
        //
        		    keywordKind[365] = (TK_MOVEMENT);
        		  
        		
        //
        // Rule 366:  KeyWord ::= M U L T I S E T
        //
        		    keywordKind[366] = (TK_MULTISET);
        		  
        		
        //
        // Rule 367:  KeyWord ::= N
        //
        		    keywordKind[367] = (TK_N);
        		  
        		
        //
        // Rule 368:  KeyWord ::= N A M E
        //
        		    keywordKind[368] = (TK_NAME);
        		  
        		
        //
        // Rule 369:  KeyWord ::= N E W
        //
        		    keywordKind[369] = (TK_NEW);
        		  
        		
        //
        // Rule 370:  KeyWord ::= N E X T
        //
        		    keywordKind[370] = (TK_NEXT);
        		  
        		
        //
        // Rule 371:  KeyWord ::= N E X T V A L
        //
        		    keywordKind[371] = (TK_NEXTVAL);
        		  
        		
        //
        // Rule 372:  KeyWord ::= N I C K N A M E
        //
        		    keywordKind[372] = (TK_NICKNAME);
        		  
        		
        //
        // Rule 373:  KeyWord ::= N O
        //
        		    keywordKind[373] = (TK_NO);
        		  
        		
        //
        // Rule 374:  KeyWord ::= N O C A C H E
        //
        		    keywordKind[374] = (TK_NOCACHE);
        		  
        		
        //
        // Rule 375:  KeyWord ::= N O C Y C L E
        //
        		    keywordKind[375] = (TK_NOCYCLE);
        		  
        		
        //
        // Rule 376:  KeyWord ::= N O D E
        //
        		    keywordKind[376] = (TK_NODE);
        		  
        		
        //
        // Rule 377:  KeyWord ::= N O D E S
        //
        		    keywordKind[377] = (TK_NODES);
        		  
        		
        //
        // Rule 378:  KeyWord ::= N O D E G R O U P
        //
        		    keywordKind[378] = (TK_NODEGROUP);
        		  
        		
        //
        // Rule 379:  KeyWord ::= N O M A X V A L U E
        //
        		    keywordKind[379] = (TK_NOMAXVALUE);
        		  
        		
        //
        // Rule 380:  KeyWord ::= N O M I N V A L U E
        //
        		    keywordKind[380] = (TK_NOMINVALUE);
        		  
        		
        //
        // Rule 381:  KeyWord ::= N O N B L O C K E D
        //
        		    keywordKind[381] = (TK_NONBLOCKED);
        		  
        		
        //
        // Rule 382:  KeyWord ::= N O N E
        //
        		    keywordKind[382] = (TK_NONE);
        		  
        		
        //
        // Rule 383:  KeyWord ::= N O O R D E R
        //
        		    keywordKind[383] = (TK_NOORDER);
        		  
        		
        //
        // Rule 384:  KeyWord ::= N O T
        //
        		    keywordKind[384] = (TK_NOT);
        		  
        		
        //
        // Rule 385:  KeyWord ::= N U L L
        //
        		    keywordKind[385] = (TK_NULL);
        		  
        		
        //
        // Rule 386:  KeyWord ::= N U L L S
        //
        		    keywordKind[386] = (TK_NULLS);
        		  
        		
        //
        // Rule 387:  KeyWord ::= N U M
        //
        		    keywordKind[387] = (TK_NUM);
        		  
        		
        //
        // Rule 388:  KeyWord ::= N U M B L O C K P A G E S
        //
        		    keywordKind[388] = (TK_NUMBLOCKPAGES);
        		  
        		
        //
        // Rule 389:  KeyWord ::= N U M E R I C
        //
        		    keywordKind[389] = (TK_NUMERIC);
        		  
        		
        //
        // Rule 390:  KeyWord ::= O B J E C T
        //
        		    keywordKind[390] = (TK_OBJECT);
        		  
        		
        //
        // Rule 391:  KeyWord ::= O F
        //
        		    keywordKind[391] = (TK_OF);
        		  
        		
        //
        // Rule 392:  KeyWord ::= O F F
        //
        		    keywordKind[392] = (TK_OFF);
        		  
        		
        //
        // Rule 393:  KeyWord ::= O L D
        //
        		    keywordKind[393] = (TK_OLD);
        		  
        		
        //
        // Rule 394:  KeyWord ::= O L E
        //
        		    keywordKind[394] = (TK_OLE);
        		  
        		
        //
        // Rule 395:  KeyWord ::= O L E D B
        //
        		    keywordKind[395] = (TK_OLEDB);
        		  
        		
        //
        // Rule 396:  KeyWord ::= O N
        //
        		    keywordKind[396] = (TK_ON);
        		  
        		
        //
        // Rule 397:  KeyWord ::= O N C E
        //
        		    keywordKind[397] = (TK_ONCE);
        		  
        		
        //
        // Rule 398:  KeyWord ::= O N L I N E
        //
        		    keywordKind[398] = (TK_ONLINE);
        		  
        		
        //
        // Rule 399:  KeyWord ::= O N L Y
        //
        		    keywordKind[399] = (TK_ONLY);
        		  
        		
        //
        // Rule 400:  KeyWord ::= O P E N
        //
        		    keywordKind[400] = (TK_OPEN);
        		  
        		
        //
        // Rule 401:  KeyWord ::= O P T I M I Z A T I O N
        //
        		    keywordKind[401] = (TK_OPTIMIZATION);
        		  
        		
        //
        // Rule 402:  KeyWord ::= O P T I M I Z E
        //
        		    keywordKind[402] = (TK_OPTIMIZE);
        		  
        		
        //
        // Rule 403:  KeyWord ::= O P T I O N
        //
        		    keywordKind[403] = (TK_OPTION);
        		  
        		
        //
        // Rule 404:  KeyWord ::= O R
        //
        		    keywordKind[404] = (TK_OR);
        		  
        		
        //
        // Rule 405:  KeyWord ::= O R D E R
        //
        		    keywordKind[405] = (TK_ORDER);
        		  
        		
        //
        // Rule 406:  KeyWord ::= O R G A N I Z E
        //
        		    keywordKind[406] = (TK_ORGANIZE);
        		  
        		
        //
        // Rule 407:  KeyWord ::= O U T
        //
        		    keywordKind[407] = (TK_OUT);
        		  
        		
        //
        // Rule 408:  KeyWord ::= O U T E R
        //
        		    keywordKind[408] = (TK_OUTER);
        		  
        		
        //
        // Rule 409:  KeyWord ::= O V E R
        //
        		    keywordKind[409] = (TK_OVER);
        		  
        		
        //
        // Rule 410:  KeyWord ::= O V E R F L O W
        //
        		    keywordKind[410] = (TK_OVERFLOW);
        		  
        		
        //
        // Rule 411:  KeyWord ::= O V E R H E A D
        //
        		    keywordKind[411] = (TK_OVERHEAD);
        		  
        		
        //
        // Rule 412:  KeyWord ::= O V E R R I D I N G
        //
        		    keywordKind[412] = (TK_OVERRIDING);
        		  
        		
        //
        // Rule 413:  KeyWord ::= P
        //
        		    keywordKind[413] = (TK_P);
        		  
        		
        //
        // Rule 414:  KeyWord ::= P A C K A G E
        //
        		    keywordKind[414] = (TK_PACKAGE);
        		  
        		
        //
        // Rule 415:  KeyWord ::= P A G E
        //
        		    keywordKind[415] = (TK_PAGE);
        		  
        		
        //
        // Rule 416:  KeyWord ::= P A G E S I Z E
        //
        		    keywordKind[416] = (TK_PAGESIZE);
        		  
        		
        //
        // Rule 417:  KeyWord ::= P A R A L L E L
        //
        		    keywordKind[417] = (TK_PARALLEL);
        		  
        		
        //
        // Rule 418:  KeyWord ::= P A R A M E T E R
        //
        		    keywordKind[418] = (TK_PARAMETER);
        		  
        		
        //
        // Rule 419:  KeyWord ::= P A S S T H R U
        //
        		    keywordKind[419] = (TK_PASSTHRU);
        		  
        		
        //
        // Rule 420:  KeyWord ::= P A S S W O R D
        //
        		    keywordKind[420] = (TK_PASSWORD);
        		  
        		
        //
        // Rule 421:  KeyWord ::= P A T H
        //
        		    keywordKind[421] = (TK_PATH);
        		  
        		
        //
        // Rule 422:  KeyWord ::= P A R T I T I O N
        //
        		    keywordKind[422] = (TK_PARTITION);
        		  
        		
        //
        // Rule 423:  KeyWord ::= P A R T I T I O N I N G
        //
        		    keywordKind[423] = (TK_PARTITIONING);
        		  
        		
        //
        // Rule 424:  KeyWord ::= P A R T I T I O N I N G S
        //
        		    keywordKind[424] = (TK_PARTITIONINGS);
        		  
        		
        //
        // Rule 425:  KeyWord ::= P A T R O L L E R
        //
        		    keywordKind[425] = (TK_PATROLLER);
        		  
        		
        //
        // Rule 426:  KeyWord ::= P C T D E A C T I V A T E
        //
        		    keywordKind[426] = (TK_PCTDEACTIVATE);
        		  
        		
        //
        // Rule 427:  KeyWord ::= P C T F R E E
        //
        		    keywordKind[427] = (TK_PCTFREE);
        		  
        		
        //
        // Rule 428:  KeyWord ::= P E N D I N G
        //
        		    keywordKind[428] = (TK_PENDING);
        		  
        		
        //
        // Rule 429:  KeyWord ::= P E R M I S S I O N
        //
        		    keywordKind[429] = (TK_PERMISSION);
        		  
        		
        //
        // Rule 430:  KeyWord ::= P I E C E S I Z E
        //
        		    keywordKind[430] = (TK_PIECESIZE);
        		  
        		
        //
        // Rule 431:  KeyWord ::= P I P E
        //
        		    keywordKind[431] = (TK_PIPE);
        		  
        		
        //
        // Rule 432:  KeyWord ::= P L A N
        //
        		    keywordKind[432] = (TK_PLAN);
        		  
        		
        //
        // Rule 433:  KeyWord ::= P R E C E D I N G
        //
        		    keywordKind[433] = (TK_PRECEDING);
        		  
        		
        //
        // Rule 434:  KeyWord ::= P R E C I S I O N
        //
        		    keywordKind[434] = (TK_PRECISION);
        		  
        		
        //
        // Rule 435:  KeyWord ::= P R E D I C A T E S
        //
        		    keywordKind[435] = (TK_PREDICATES);
        		  
        		
        //
        // Rule 436:  KeyWord ::= P R E F E T C H S I Z E
        //
        		    keywordKind[436] = (TK_PREFETCHSIZE);
        		  
        		
        //
        // Rule 437:  KeyWord ::= P R E P A R E
        //
        		    keywordKind[437] = (TK_PREPARE);
        		  
        		
        //
        // Rule 438:  KeyWord ::= P R E S E R V E
        //
        		    keywordKind[438] = (TK_PRESERVE);
        		  
        		
        //
        // Rule 439:  KeyWord ::= P R E V I O U S
        //
        		    keywordKind[439] = (TK_PREVIOUS);
        		  
        		
        //
        // Rule 440:  KeyWord ::= P R E V V A L
        //
        		    keywordKind[440] = (TK_PREVVAL);
        		  
        		
        //
        // Rule 441:  KeyWord ::= P R I M A R Y
        //
        		    keywordKind[441] = (TK_PRIMARY);
        		  
        		
        //
        // Rule 442:  KeyWord ::= P R I Q T Y
        //
        		    keywordKind[442] = (TK_PRIQTY);
        		  
        		
        //
        // Rule 443:  KeyWord ::= P R I V I L E G E S
        //
        		    keywordKind[443] = (TK_PRIVILEGES);
        		  
        		
        //
        // Rule 444:  KeyWord ::= P R O C E D U R E
        //
        		    keywordKind[444] = (TK_PROCEDURE);
        		  
        		
        //
        // Rule 445:  KeyWord ::= P R O F I L E
        //
        		    keywordKind[445] = (TK_PROFILE);
        		  
        		
        //
        // Rule 446:  KeyWord ::= P R O G R A M
        //
        		    keywordKind[446] = (TK_PROGRAM);
        		  
        		
        //
        // Rule 447:  KeyWord ::= P R O P A G A T E
        //
        		    keywordKind[447] = (TK_PROPAGATE);
        		  
        		
        //
        // Rule 448:  KeyWord ::= P R O T O C O L
        //
        		    keywordKind[448] = (TK_PROTOCOL);
        		  
        		
        //
        // Rule 449:  KeyWord ::= P R U N E
        //
        		    keywordKind[449] = (TK_PRUNE);
        		  
        		
        //
        // Rule 450:  KeyWord ::= P U B L I C
        //
        		    keywordKind[450] = (TK_PUBLIC);
        		  
        		
        //
        // Rule 451:  KeyWord ::= Q U E R Y
        //
        		    keywordKind[451] = (TK_QUERY);
        		  
        		
        //
        // Rule 452:  KeyWord ::= Q U E R Y N O
        //
        		    keywordKind[452] = (TK_QUERYNO);
        		  
        		
        //
        // Rule 453:  KeyWord ::= Q U E R Y T A G
        //
        		    keywordKind[453] = (TK_QUERYTAG);
        		  
        		
        //
        // Rule 454:  KeyWord ::= R A N G E
        //
        		    keywordKind[454] = (TK_RANGE);
        		  
        		
        //
        // Rule 455:  KeyWord ::= R A T E
        //
        		    keywordKind[455] = (TK_RATE);
        		  
        		
        //
        // Rule 456:  KeyWord ::= R A T I O
        //
        		    keywordKind[456] = (TK_RATIO);
        		  
        		
        //
        // Rule 457:  KeyWord ::= R E A D
        //
        		    keywordKind[457] = (TK_READ);
        		  
        		
        //
        // Rule 458:  KeyWord ::= R E A D S
        //
        		    keywordKind[458] = (TK_READS);
        		  
        		
        //
        // Rule 459:  KeyWord ::= R E A L
        //
        		    keywordKind[459] = (TK_REAL);
        		  
        		
        //
        // Rule 460:  KeyWord ::= R E C O M M E N D
        //
        		    keywordKind[460] = (TK_RECOMMEND);
        		  
        		
        //
        // Rule 461:  KeyWord ::= R E C O N C I L E
        //
        		    keywordKind[461] = (TK_RECONCILE);
        		  
        		
        //
        // Rule 462:  KeyWord ::= R E C O V E R Y
        //
        		    keywordKind[462] = (TK_RECOVERY);
        		  
        		
        //
        // Rule 463:  KeyWord ::= R E C R E A T E
        //
        		    keywordKind[463] = (TK_RECREATE);
        		  
        		
        //
        // Rule 464:  KeyWord ::= R E D U C E
        //
        		    keywordKind[464] = (TK_REDUCE);
        		  
        		
        //
        // Rule 465:  KeyWord ::= R E F
        //
        		    keywordKind[465] = (TK_REF);
        		  
        		
        //
        // Rule 466:  KeyWord ::= R E F E R E N C E
        //
        		    keywordKind[466] = (TK_REFERENCE);
        		  
        		
        //
        // Rule 467:  KeyWord ::= R E F E R E N C E S
        //
        		    keywordKind[467] = (TK_REFERENCES);
        		  
        		
        //
        // Rule 468:  KeyWord ::= R E F E R E N C I N G
        //
        		    keywordKind[468] = (TK_REFERENCING);
        		  
        		
        //
        // Rule 469:  KeyWord ::= R E F R E S H
        //
        		    keywordKind[469] = (TK_REFRESH);
        		  
        		
        //
        // Rule 470:  KeyWord ::= R E G I S T E R S
        //
        		    keywordKind[470] = (TK_REGISTERS);
        		  
        		
        //
        // Rule 471:  KeyWord ::= R E G U L A R
        //
        		    keywordKind[471] = (TK_REGULAR);
        		  
        		
        //
        // Rule 472:  KeyWord ::= R E L E A S E
        //
        		    keywordKind[472] = (TK_RELEASE);
        		  
        		
        //
        // Rule 473:  KeyWord ::= R E M A I N
        //
        		    keywordKind[473] = (TK_REMAIN);
        		  
        		
        //
        // Rule 474:  KeyWord ::= R E M O T E
        //
        		    keywordKind[474] = (TK_REMOTE);
        		  
        		
        //
        // Rule 475:  KeyWord ::= R E N A M E
        //
        		    keywordKind[475] = (TK_RENAME);
        		  
        		
        //
        // Rule 476:  KeyWord ::= R E O P T
        //
        		    keywordKind[476] = (TK_REOPT);
        		  
        		
        //
        // Rule 477:  KeyWord ::= R E P E A T A B L E
        //
        		    keywordKind[477] = (TK_REPEATABLE);
        		  
        		
        //
        // Rule 478:  KeyWord ::= R E P E A T
        //
        		    keywordKind[478] = (TK_REPEAT);
        		  
        		
        //
        // Rule 479:  KeyWord ::= R E P L A C E
        //
        		    keywordKind[479] = (TK_REPLACE);
        		  
        		
        //
        // Rule 480:  KeyWord ::= R E P L I C A T E D
        //
        		    keywordKind[480] = (TK_REPLICATED);
        		  
        		
        //
        // Rule 481:  KeyWord ::= R E Q U I R I N G
        //
        		    keywordKind[481] = (TK_REQUIRING);
        		  
        		
        //
        // Rule 482:  KeyWord ::= R E S E T
        //
        		    keywordKind[482] = (TK_RESET);
        		  
        		
        //
        // Rule 483:  KeyWord ::= R E S I D E N T
        //
        		    keywordKind[483] = (TK_RESIDENT);
        		  
        		
        //
        // Rule 484:  KeyWord ::= R E S I G N A L
        //
        		    keywordKind[484] = (TK_RESIGNAL);
        		  
        		
        //
        // Rule 485:  KeyWord ::= R E S I Z E
        //
        		    keywordKind[485] = (TK_RESIZE);
        		  
        		
        //
        // Rule 486:  KeyWord ::= R E S O L V E
        //
        		    keywordKind[486] = (TK_RESOLVE);
        		  
        		
        //
        // Rule 487:  KeyWord ::= R E S T A R T
        //
        		    keywordKind[487] = (TK_RESTART);
        		  
        		
        //
        // Rule 488:  KeyWord ::= R E S T O R E
        //
        		    keywordKind[488] = (TK_RESTORE);
        		  
        		
        //
        // Rule 489:  KeyWord ::= R E S T R I C T
        //
        		    keywordKind[489] = (TK_RESTRICT);
        		  
        		
        //
        // Rule 490:  KeyWord ::= R E S U L T
        //
        		    keywordKind[490] = (TK_RESULT);
        		  
        		
        //
        // Rule 491:  KeyWord ::= R E T A I N
        //
        		    keywordKind[491] = (TK_RETAIN);
        		  
        		
        //
        // Rule 492:  KeyWord ::= R E T U R N
        //
        		    keywordKind[492] = (TK_RETURN);
        		  
        		
        //
        // Rule 493:  KeyWord ::= R E T U R N S
        //
        		    keywordKind[493] = (TK_RETURNS);
        		  
        		
        //
        // Rule 494:  KeyWord ::= R E V E R S E
        //
        		    keywordKind[494] = (TK_REVERSE);
        		  
        		
        //
        // Rule 495:  KeyWord ::= R E V O K E
        //
        		    keywordKind[495] = (TK_REVOKE);
        		  
        		
        //
        // Rule 496:  KeyWord ::= R I G H T
        //
        		    keywordKind[496] = (TK_RIGHT);
        		  
        		
        //
        // Rule 497:  KeyWord ::= R O L L B A C K
        //
        		    keywordKind[497] = (TK_ROLLBACK);
        		  
        		
        //
        // Rule 498:  KeyWord ::= R O L L U P
        //
        		    keywordKind[498] = (TK_ROLLUP);
        		  
        		
        //
        // Rule 499:  KeyWord ::= R O U T I N E
        //
        		    keywordKind[499] = (TK_ROUTINE);
        		  
        		
        //
        // Rule 500:  KeyWord ::= R O W
        //
        		    keywordKind[500] = (TK_ROW);
        		  
        		
        //
        // Rule 501:  KeyWord ::= R O W S
        //
        		    keywordKind[501] = (TK_ROWS);
        		  
        		
        //
        // Rule 502:  KeyWord ::= R R
        //
        		    keywordKind[502] = (TK_RR);
        		  
        		
        //
        // Rule 503:  KeyWord ::= R S
        //
        		    keywordKind[503] = (TK_RS);
        		  
        		
        //
        // Rule 504:  KeyWord ::= R U N
        //
        		    keywordKind[504] = (TK_RUN);
        		  
        		
        //
        // Rule 505:  KeyWord ::= S
        //
        		    keywordKind[505] = (TK_S);
        		  
        		
        //
        // Rule 506:  KeyWord ::= S A M P L E D
        //
        		    keywordKind[506] = (TK_SAMPLED);
        		  
        		
        //
        // Rule 507:  KeyWord ::= S A V E P O I N T
        //
        		    keywordKind[507] = (TK_SAVEPOINT);
        		  
        		
        //
        // Rule 508:  KeyWord ::= S B C S
        //
        		    keywordKind[508] = (TK_SBCS);
        		  
        		
        //
        // Rule 509:  KeyWord ::= S C A N S
        //
        		    keywordKind[509] = (TK_SCANS);
        		  
        		
        //
        // Rule 510:  KeyWord ::= S C H E M A
        //
        		    keywordKind[510] = (TK_SCHEMA);
        		  
        		
        //
        // Rule 511:  KeyWord ::= S C O P E
        //
        		    keywordKind[511] = (TK_SCOPE);
        		  
        		
        //
        // Rule 512:  KeyWord ::= S C R A T C H P A D
        //
        		    keywordKind[512] = (TK_SCRATCHPAD);
        		  
        		
        //
        // Rule 513:  KeyWord ::= S C R O L L
        //
        		    keywordKind[513] = (TK_SCROLL);
        		  
        		
        //
        // Rule 514:  KeyWord ::= S E A R C H
        //
        		    keywordKind[514] = (TK_SEARCH);
        		  
        		
        //
        // Rule 515:  KeyWord ::= S E C O N D
        //
        		    keywordKind[515] = (TK_SECOND);
        		  
        		
        //
        // Rule 516:  KeyWord ::= S E C O N D S
        //
        		    keywordKind[516] = (TK_SECONDS);
        		  
        		
        //
        // Rule 517:  KeyWord ::= S E C Q T Y
        //
        		    keywordKind[517] = (TK_SECQTY);
        		  
        		
        //
        // Rule 518:  KeyWord ::= S E C U R I T Y A D M
        //
        		    keywordKind[518] = (TK_SECURITYADM);
        		  
        		
        //
        // Rule 519:  KeyWord ::= S E L E C T
        //
        		    keywordKind[519] = (TK_SELECT);
        		  
        		
        //
        // Rule 520:  KeyWord ::= S E L E C T I O N
        //
        		    keywordKind[520] = (TK_SELECTION);
        		  
        		
        //
        // Rule 521:  KeyWord ::= S E L E C T I V E
        //
        		    keywordKind[521] = (TK_SELECTIVE);
        		  
        		
        //
        // Rule 522:  KeyWord ::= S E L E C T I V I T Y
        //
        		    keywordKind[522] = (TK_SELECTIVITY);
        		  
        		
        //
        // Rule 523:  KeyWord ::= S E L F
        //
        		    keywordKind[523] = (TK_SELF);
        		  
        		
        //
        // Rule 524:  KeyWord ::= S E N S I T I V E
        //
        		    keywordKind[524] = (TK_SENSITIVE);
        		  
        		
        //
        // Rule 525:  KeyWord ::= S E Q U E N C E
        //
        		    keywordKind[525] = (TK_SEQUENCE);
        		  
        		
        //
        // Rule 526:  KeyWord ::= S E R I A L I Z A B L E
        //
        		    keywordKind[526] = (TK_SERIALIZABLE);
        		  
        		
        //
        // Rule 527:  KeyWord ::= S E R V E R
        //
        		    keywordKind[527] = (TK_SERVER);
        		  
        		
        //
        // Rule 528:  KeyWord ::= S E S S I O N
        //
        		    keywordKind[528] = (TK_SESSION);
        		  
        		
        //
        // Rule 529:  KeyWord ::= S E S S I O N _ U S E R
        //
                   keywordKind[529] = (TK_SESSION_USER);
                  
                
        //
        // Rule 530:  KeyWord ::= S E T
        //
        		    keywordKind[530] = (TK_SET);
        		  
        		
        //
        // Rule 531:  KeyWord ::= S E T S
        //
        		    keywordKind[531] = (TK_SETS);
        		  
        		
        //
        // Rule 532:  KeyWord ::= S E T T I N G
        //
        		    keywordKind[532] = (TK_SETTING);
        		  
        		
        //
        // Rule 533:  KeyWord ::= S H A R E
        //
        		    keywordKind[533] = (TK_SHARE);
        		  
        		
        //
        // Rule 534:  KeyWord ::= S H R L E V E L
        //
        		    keywordKind[534] = (TK_SHRLEVEL);
        		  
        		
        //
        // Rule 535:  KeyWord ::= S I G N A L
        //
        		    keywordKind[535] = (TK_SIGNAL);
        		  
        		
        //
        // Rule 536:  KeyWord ::= S I M P L E
        //
        		    keywordKind[536] = (TK_SIMPLE);
        		  
        		
        //
        // Rule 537:  KeyWord ::= S I Z E
        //
        		    keywordKind[537] = (TK_SIZE);
        		  
        		
        //
        // Rule 538:  KeyWord ::= S M A L L I N T
        //
        		    keywordKind[538] = (TK_SMALLINT);
        		  
        		
        //
        // Rule 539:  KeyWord ::= S N A P S H O T
        //
        		    keywordKind[539] = (TK_SNAPSHOT);
        		  
        		
        //
        // Rule 540:  KeyWord ::= S O M E
        //
        		    keywordKind[540] = (TK_SOME);
        		  
        		
        //
        // Rule 541:  KeyWord ::= S O U R C E
        //
        		    keywordKind[541] = (TK_SOURCE);
        		  
        		
        //
        // Rule 542:  KeyWord ::= S P E C I A L
        //
        		    keywordKind[542] = (TK_SPECIAL);
        		  
        		
        //
        // Rule 543:  KeyWord ::= S P E C I F I C
        //
        		    keywordKind[543] = (TK_SPECIFIC);
        		  
        		
        //
        // Rule 544:  KeyWord ::= S P E C I F I C A T I O N
        //
        		    keywordKind[544] = (TK_SPECIFICATION);
        		  
        		
        //
        // Rule 545:  KeyWord ::= S P L I T
        //
        		    keywordKind[545] = (TK_SPLIT);
        		  
        		
        //
        // Rule 546:  KeyWord ::= S Q L
        //
        		    keywordKind[546] = (TK_SQL);
        		  
        		
        //
        // Rule 547:  KeyWord ::= S Q L D A T A
        //
        		    keywordKind[547] = (TK_SQLDATA);
        		  
        		
        //
        // Rule 548:  KeyWord ::= S Q L E R R O R
        //
        		    keywordKind[548] = (TK_SQLERROR);
        		  
        		
        //
        // Rule 549:  KeyWord ::= S Q L E X C E P T I O N
        //
        		    keywordKind[549] = (TK_SQLEXCEPTION);
        		  
        		
        //
        // Rule 550:  KeyWord ::= S Q L W A R N I N G
        //
        		    keywordKind[550] = (TK_SQLWARNING);
        		  
        		
        //
        // Rule 551:  KeyWord ::= S Q L I D
        //
        		    keywordKind[551] = (TK_SQLID);
        		  
        		
        //
        // Rule 552:  KeyWord ::= S Q L M A C R O
        //
        		    keywordKind[552] = (TK_SQLMACRO);
        		  
        		
        //
        // Rule 553:  KeyWord ::= S Q L S T A T E
        //
        		    keywordKind[553] = (TK_SQLSTATE);
        		  
        		
        //
        // Rule 554:  KeyWord ::= S S A
        //
        		    keywordKind[554] = (TK_SSA);
        		  
        		
        //
        // Rule 555:  KeyWord ::= S T A B I L I T Y
        //
        		    keywordKind[555] = (TK_STABILITY);
        		  
        		
        //
        // Rule 556:  KeyWord ::= S T A R T
        //
        		    keywordKind[556] = (TK_START);
        		  
        		
        //
        // Rule 557:  KeyWord ::= S T A T E
        //
        		    keywordKind[557] = (TK_STATE);
        		  
        		
        //
        // Rule 558:  KeyWord ::= S T A T E M E N T
        //
        		    keywordKind[558] = (TK_STATEMENT);
        		  
        		
        //
        // Rule 559:  KeyWord ::= S T A T E M E N T S
        //
        		    keywordKind[559] = (TK_STATEMENTS);
        		  
        		
        //
        // Rule 560:  KeyWord ::= S T A T I C
        //
        		    keywordKind[560] = (TK_STATIC);
        		  
        		
        //
        // Rule 561:  KeyWord ::= S T A T I S T I C S
        //
        		    keywordKind[561] = (TK_STATISTICS);
        		  
        		
        //
        // Rule 562:  KeyWord ::= S T A R T I N G
        //
        		    keywordKind[562] = (TK_STARTING);
        		  
        		
        //
        // Rule 563:  KeyWord ::= S T A Y
        //
        		    keywordKind[563] = (TK_STAY);
        		  
        		
        //
        // Rule 564:  KeyWord ::= S T M T
        //
        		    keywordKind[564] = (TK_STMT);
        		  
        		
        //
        // Rule 565:  KeyWord ::= S T O G R O U P
        //
        		    keywordKind[565] = (TK_STOGROUP);
        		  
        		
        //
        // Rule 566:  KeyWord ::= S T O R A G E
        //
        		    keywordKind[566] = (TK_STORAGE);
        		  
        		
        //
        // Rule 567:  KeyWord ::= S T O R E D
        //
        		    keywordKind[567] = (TK_STORED);
        		  
        		
        //
        // Rule 568:  KeyWord ::= S T R I P
        //
        		    keywordKind[568] = (TK_STRIP);
        		  
        		
        //
        // Rule 569:  KeyWord ::= S T R I P E
        //
        		    keywordKind[569] = (TK_STRIPE);
        		  
        		
        //
        // Rule 570:  KeyWord ::= S T Y L E
        //
        		    keywordKind[570] = (TK_STYLE);
        		  
        		
        //
        // Rule 571:  KeyWord ::= S U B
        //
        		    keywordKind[571] = (TK_SUB);
        		  
        		
        //
        // Rule 572:  KeyWord ::= S U B S E C T I O N
        //
        		    keywordKind[572] = (TK_SUBSECTION);
        		  
        		
        //
        // Rule 573:  KeyWord ::= S U M M A R Y
        //
        		    keywordKind[573] = (TK_SUMMARY);
        		  
        		
        //
        // Rule 574:  KeyWord ::= S W I T C H
        //
        		    keywordKind[574] = (TK_SWITCH);
        		  
        		
        //
        // Rule 575:  KeyWord ::= S Y M M E T R I C
        //
        		    keywordKind[575] = (TK_SYMMETRIC);
        		  
        		
        //
        // Rule 576:  KeyWord ::= S Y N O N Y M
        //
        		    keywordKind[576] = (TK_SYNONYM);
        		  
        		
        //
        // Rule 577:  KeyWord ::= S Y S T E M
        //
        		    keywordKind[577] = (TK_SYSTEM);
        		  
        		
        //
        // Rule 578:  KeyWord ::= S Y S T E M _ U S E R
        //
                   keywordKind[578] = (TK_SYSTEM_USER);
                  
                
        //
        // Rule 579:  KeyWord ::= T
        //
        		    keywordKind[579] = (TK_T);
        		  
        		
        //
        // Rule 580:  KeyWord ::= T A B L E
        //
        		    keywordKind[580] = (TK_TABLE);
        		  
        		
        //
        // Rule 581:  KeyWord ::= T A B L E S
        //
        		    keywordKind[581] = (TK_TABLES);
        		  
        		
        //
        // Rule 582:  KeyWord ::= T A B L E S A M P L E
        //
        		    keywordKind[582] = (TK_TABLESAMPLE);
        		  
        		
        //
        // Rule 583:  KeyWord ::= T A B L E S P A C E
        //
        		    keywordKind[583] = (TK_TABLESPACE);
        		  
        		
        //
        // Rule 584:  KeyWord ::= T A B L E S P A C E S
        //
        		    keywordKind[584] = (TK_TABLESPACES);
        		  
        		
        //
        // Rule 585:  KeyWord ::= T A R G E T
        //
        		    keywordKind[585] = (TK_TARGET);
        		  
        		
        //
        // Rule 586:  KeyWord ::= T E M P L A T E
        //
        		    keywordKind[586] = (TK_TEMPLATE);
        		  
        		
        //
        // Rule 587:  KeyWord ::= T E M P O R A R Y
        //
        		    keywordKind[587] = (TK_TEMPORARY);
        		  
        		
        //
        // Rule 588:  KeyWord ::= T H E N
        //
        		    keywordKind[588] = (TK_THEN);
        		  
        		
        //
        // Rule 589:  KeyWord ::= T H R E A D S A F E
        //
        		    keywordKind[589] = (TK_THREADSAFE);
        		  
        		
        //
        // Rule 590:  KeyWord ::= T H R O U G H
        //
        		    keywordKind[590] = (TK_THROUGH);
        		  
        		
        //
        // Rule 591:  KeyWord ::= T I M E
        //
        		    keywordKind[591] = (TK_TIME);
        		  
        		
        //
        // Rule 592:  KeyWord ::= T I M E O U T
        //
        		    keywordKind[592] = (TK_TIMEOUT);
        		  
        		
        //
        // Rule 593:  KeyWord ::= T I M E S T A M P
        //
        		    keywordKind[593] = (TK_TIMESTAMP);
        		  
        		
        //
        // Rule 594:  KeyWord ::= T I M E Z O N E
        //
        		    keywordKind[594] = (TK_TIMEZONE);
        		  
        		
        //
        // Rule 595:  KeyWord ::= T O
        //
        		    keywordKind[595] = (TK_TO);
        		  
        		
        //
        // Rule 596:  KeyWord ::= T O K E N
        //
        		    keywordKind[596] = (TK_TOKEN);
        		  
        		
        //
        // Rule 597:  KeyWord ::= T R A I L I N G
        //
        		    keywordKind[597] = (TK_TRAILING);
        		  
        		
        //
        // Rule 598:  KeyWord ::= T R A N S A C T I O N S
        //
        		    keywordKind[598] = (TK_TRANSACTIONS);
        		  
        		
        //
        // Rule 599:  KeyWord ::= T R A N S F E R R A T E
        //
        		    keywordKind[599] = (TK_TRANSFERRATE);
        		  
        		
        //
        // Rule 600:  KeyWord ::= T R A N S F O R M
        //
        		    keywordKind[600] = (TK_TRANSFORM);
        		  
        		
        //
        // Rule 601:  KeyWord ::= T R A N S F O R M S
        //
        		    keywordKind[601] = (TK_TRANSFORMS);
        		  
        		
        //
        // Rule 602:  KeyWord ::= T R A N S L A T E
        //
        		    keywordKind[602] = (TK_TRANSLATE);
        		  
        		
        //
        // Rule 603:  KeyWord ::= T R E A T
        //
        		    keywordKind[603] = (TK_TREAT);
        		  
        		
        //
        // Rule 604:  KeyWord ::= T R I G G E R
        //
        		    keywordKind[604] = (TK_TRIGGER);
        		  
        		
        //
        // Rule 605:  KeyWord ::= T R U E
        //
        		    keywordKind[605] = (TK_TRUE);
        		  
        		
        //
        // Rule 606:  KeyWord ::= T R U N C
        //
        		    keywordKind[606] = (TK_TRUNC);
        		  
        		
        //
        // Rule 607:  KeyWord ::= T W O L E V E L
        //
        		    keywordKind[607] = (TK_TWOLEVEL);
        		  
        		
        //
        // Rule 608:  KeyWord ::= T Y P E
        //
        		    keywordKind[608] = (TK_TYPE);
        		  
        		
        //
        // Rule 609:  KeyWord ::= T Y P E S
        //
        		    keywordKind[609] = (TK_TYPES);
        		  
        		
        //
        // Rule 610:  KeyWord ::= U N B O U N D E D
        //
        		    keywordKind[610] = (TK_UNBOUNDED);
        		  
        		
        //
        // Rule 611:  KeyWord ::= U N C H E C K E D
        //
        		    keywordKind[611] = (TK_UNCHECKED);
        		  
        		
        //
        // Rule 612:  KeyWord ::= U N C O M M I T T E D
        //
        		    keywordKind[612] = (TK_UNCOMMITTED);
        		  
        		
        //
        // Rule 613:  KeyWord ::= U N D O
        //
        		    keywordKind[613] = (TK_UNDO);
        		  
        		
        //
        // Rule 614:  KeyWord ::= U N I C O D E
        //
        		    keywordKind[614] = (TK_UNICODE);
        		  
        		
        //
        // Rule 615:  KeyWord ::= U N I O N
        //
        		    keywordKind[615] = (TK_UNION);
        		  
        		
        //
        // Rule 616:  KeyWord ::= U N I Q U E
        //
        		    keywordKind[616] = (TK_UNIQUE);
        		  
        		
        //
        // Rule 617:  KeyWord ::= U N L I N K
        //
        		    keywordKind[617] = (TK_UNLINK);
        		  
        		
        //
        // Rule 618:  KeyWord ::= U N T I L
        //
        		    keywordKind[618] = (TK_UNTIL);
        		  
        		
        //
        // Rule 619:  KeyWord ::= U P D A T E
        //
        		    keywordKind[619] = (TK_UPDATE);
        		  
        		
        //
        // Rule 620:  KeyWord ::= U R
        //
        		    keywordKind[620] = (TK_UR);
        		  
        		
        //
        // Rule 621:  KeyWord ::= U R L
        //
        		    keywordKind[621] = (TK_URL);
        		  
        		
        //
        // Rule 622:  KeyWord ::= U S A
        //
        		    keywordKind[622] = (TK_USA);
        		  
        		
        //
        // Rule 623:  KeyWord ::= U S E
        //
        		    keywordKind[623] = (TK_USE);
        		  
        		
        //
        // Rule 624:  KeyWord ::= U S A G E
        //
        		    keywordKind[624] = (TK_USAGE);
        		  
        		
        //
        // Rule 625:  KeyWord ::= U S E R
        //
        		    keywordKind[625] = (TK_USER);
        		  
        		
        //
        // Rule 626:  KeyWord ::= U S E R I D
        //
        		    keywordKind[626] = (TK_USERID);
        		  
        		
        //
        // Rule 627:  KeyWord ::= U S I N G
        //
        		    keywordKind[627] = (TK_USING);
        		  
        		
        //
        // Rule 628:  KeyWord ::= V A L U E
        //
        		    keywordKind[628] = (TK_VALUE);
        		  
        		
        //
        // Rule 629:  KeyWord ::= V A L U E S
        //
        		    keywordKind[629] = (TK_VALUES);
        		  
        		
        //
        // Rule 630:  KeyWord ::= V A R C H A R
        //
        		    keywordKind[630] = (TK_VARCHAR);
        		  
        		
        //
        // Rule 631:  KeyWord ::= V A R G R A P H I C
        //
        		    keywordKind[631] = (TK_VARGRAPHIC);
        		  
        		
        //
        // Rule 632:  KeyWord ::= V A R I A N T
        //
        		    keywordKind[632] = (TK_VARIANT);
        		  
        		
        //
        // Rule 633:  KeyWord ::= V A R Y I N G
        //
        		    keywordKind[633] = (TK_VARYING);
        		  
        		
        //
        // Rule 634:  KeyWord ::= V C A T
        //
        		    keywordKind[634] = (TK_VCAT);
        		  
        		
        //
        // Rule 635:  KeyWord ::= V E R S I O N
        //
        		    keywordKind[635] = (TK_VERSION);
        		  
        		
        //
        // Rule 636:  KeyWord ::= V I E W
        //
        		    keywordKind[636] = (TK_VIEW);
        		  
        		
        //
        // Rule 637:  KeyWord ::= V O L A T I L E
        //
        		    keywordKind[637] = (TK_VOLATILE);
        		  
        		
        //
        // Rule 638:  KeyWord ::= W A I T
        //
        		    keywordKind[638] = (TK_WAIT);
        		  
        		
        //
        // Rule 639:  KeyWord ::= W H E N
        //
        		    keywordKind[639] = (TK_WHEN);
        		  
        		
        //
        // Rule 640:  KeyWord ::= W H E R E
        //
        		    keywordKind[640] = (TK_WHERE);
        		  
        		
        //
        // Rule 641:  KeyWord ::= W H I L E
        //
        		    keywordKind[641] = (TK_WHILE);
        		  
        		
        //
        // Rule 642:  KeyWord ::= W I T H
        //
        		    keywordKind[642] = (TK_WITH);
        		  
        		
        //
        // Rule 643:  KeyWord ::= W I T H O U T
        //
        		    keywordKind[643] = (TK_WITHOUT);
        		  
        		
        //
        // Rule 644:  KeyWord ::= W O R K
        //
        		    keywordKind[644] = (TK_WORK);
        		  
        		
        //
        // Rule 645:  KeyWord ::= W R A P P E R
        //
        		    keywordKind[645] = (TK_WRAPPER);
        		  
        		
        //
        // Rule 646:  KeyWord ::= W R I T E
        //
        		    keywordKind[646] = (TK_WRITE);
        		  
        		
        //
        // Rule 647:  KeyWord ::= W R K S T N N A M E
        //
        		    keywordKind[647] = (TK_WRKSTNNAME);
        		  
        		
        //
        // Rule 648:  KeyWord ::= X
        //
        		    keywordKind[648] = (TK_X);
        		  
        		
        //
        // Rule 649:  KeyWord ::= X A C T
        //
        		    keywordKind[649] = (TK_XACT);
        		  
        		
        //
        // Rule 650:  KeyWord ::= Y E A R
        //
        		    keywordKind[650] = (TK_YEAR);
        		  
        		
        //
        // Rule 651:  KeyWord ::= Y E A R S
        //
        		    keywordKind[651] = (TK_YEARS);
        		  
        		
        //
        // Rule 652:  KeyWord ::= Y E S
        //
        		    keywordKind[652] = (TK_YES);
        		  
        		
        //
        // Rule 653:  KeyWord ::= A B S E N T
        //
                    keywordKind[653] = (TK_ABSENT);
                  
                
        //
        // Rule 654:  KeyWord ::= A C C O R D I N G
        //
                    keywordKind[654] = (TK_ACCORDING);
                  
                
        //
        // Rule 655:  KeyWord ::= B A S E 6 4
        //
                    keywordKind[655] = (TK_BASE64);
                  
                
        //
        // Rule 656:  KeyWord ::= D O C U M E N T
        //
                    keywordKind[656] = (TK_DOCUMENT);
                  
                
        //
        // Rule 657:  KeyWord ::= E N C O D I N G
        //
                    keywordKind[657] = (TK_ENCODING);
                  
                
        //
        // Rule 658:  KeyWord ::= L O C A T I O N
        //
                    keywordKind[658] = (TK_LOCATION);
                  
                
        //
        // Rule 659:  KeyWord ::= H E X
        //
                    keywordKind[659] = (TK_HEX);
                  
                
        //
        // Rule 660:  KeyWord ::= N A M E S P A C E
        //
                    keywordKind[660] = (TK_NAMESPACE);
                  
                
        //
        // Rule 661:  KeyWord ::= N I L
        //
                    keywordKind[661] = (TK_NIL);
                  
                
        //
        // Rule 662:  KeyWord ::= O R D I N A L I T Y
        //
                    keywordKind[662] = (TK_ORDINALITY);
                  
                
        //
        // Rule 663:  KeyWord ::= P A S S I N G
        //
                    keywordKind[663] = (TK_PASSING);
                  
                
        //
        // Rule 664:  KeyWord ::= P I
        //
                    keywordKind[664] = (TK_PI);
                  
                
        //
        // Rule 665:  KeyWord ::= R E T U R N I N G
        //
                    keywordKind[665] = (TK_RETURNING);
                  
                
        //
        // Rule 666:  KeyWord ::= U R I
        //
                    keywordKind[666] = (TK_URI);
                  
                
        //
        // Rule 667:  KeyWord ::= W H I T E S P A C E
        //
                    keywordKind[667] = (TK_WHITESPACE);
                  
                
        //
        // Rule 668:  KeyWord ::= X M L
        //
                    keywordKind[668] = (TK_XML);
                  
                
        //
        // Rule 669:  KeyWord ::= X M L A G G
        //
        		    keywordKind[669] = (TK_XMLAGG);
        		  
        		
        //
        // Rule 670:  KeyWord ::= X M L B I N A R Y
        //
        		    keywordKind[670] = (TK_XMLBINARY);
        		  
        		
        //
        // Rule 671:  KeyWord ::= X M L A T T R I B U T E S
        //
        		    keywordKind[671] = (TK_XMLATTRIBUTES);
        		  
        		
        //
        // Rule 672:  KeyWord ::= X M L C A S T
        //
                    keywordKind[672] = (TK_XMLCAST);
                  
                
        //
        // Rule 673:  KeyWord ::= X M L C O M M E N T
        //
                    keywordKind[673] = (TK_XMLCOMMENT);
                  
                
        //
        // Rule 674:  KeyWord ::= X M L C O N C A T
        //
                    keywordKind[674] = (TK_XMLCONCAT);
                  
                
        //
        // Rule 675:  KeyWord ::= X M L D E C L A R A T I O N
        //
                    keywordKind[675] = (TK_XMLDECLARATION);
                  
                
        //
        // Rule 676:  KeyWord ::= X M L D O C U M E N T
        //
                    keywordKind[676] = (TK_XMLDOCUMENT);
                  
                
        //
        // Rule 677:  KeyWord ::= X M L E L E M E N T
        //
        		    keywordKind[677] = (TK_XMLELEMENT);
        		  
        		
        //
        // Rule 678:  KeyWord ::= X M L E X I S T S
        //
                    keywordKind[678] = (TK_XMLEXISTS);
                  
                
        //
        // Rule 679:  KeyWord ::= X M L F O R E S T
        //
        		    keywordKind[679] = (TK_XMLFOREST);
        		  
        		
        //
        // Rule 680:  KeyWord ::= X M L N A M E S P A C E S
        //
        		    keywordKind[680] = (TK_XMLNAMESPACES);
        		  
        		
        //
        // Rule 681:  KeyWord ::= X M L P A R S E
        //
                    keywordKind[681] = (TK_XMLPARSE);
                  
                
        //
        // Rule 682:  KeyWord ::= X M L P I
        //
                    keywordKind[682] = (TK_XMLPI);
                  
                
        //
        // Rule 683:  KeyWord ::= X M L Q U E R Y
        //
                    keywordKind[683] = (TK_XMLQUERY);
                  
                
        //
        // Rule 684:  KeyWord ::= X M L S C H E M A
        //
                    keywordKind[684] = (TK_XMLSCHEMA);
                  
                
        //
        // Rule 685:  KeyWord ::= X M L S E R I A L I Z E
        //
        		    keywordKind[685] = (TK_XMLSERIALIZE);
        		  
        		
        //
        // Rule 686:  KeyWord ::= X M L T A B L E
        //
                    keywordKind[686] = (TK_XMLTABLE);
                  
                
        //
        // Rule 687:  KeyWord ::= X M L T E X T
        //
                    keywordKind[687] = (TK_XMLTEXT);
                  
                
        //
        // Rule 688:  KeyWord ::= X M L V A L I D A T E
        //
                    keywordKind[688] = (TK_XMLVALIDATE);
                  
                

        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

