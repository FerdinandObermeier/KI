package savilerow.expression;
/*

    Savile Row http://savilerow.cs.st-andrews.ac.uk/
    Copyright (C) 2014-2017 Peter Nightingale
    
    This file is part of Savile Row.
    
    Savile Row is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Savile Row is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Savile Row.  If not, see <http://www.gnu.org/licenses/>.

*/


import java.util.*;
import savilerow.model.SymbolTable;
import savilerow.CmdFlags;

// One child, a compound matrix or a slice or something.

public class Minimising extends ASTNode
{
    public static final long serialVersionUID = 1L;
    public Minimising(ASTNode r)
    {
        super(r);
    }
    
    public ASTNode copy()
    {
        assert numChildren()==1;
        return new Minimising(getChild(0));
    }
    
    @Override
    public boolean typecheck(SymbolTable st) {
        if(!getChild(0).typecheck(st)) return false;
        if(getChild(0).getDimension()>0) {
            CmdFlags.println("ERROR: Expected numerical expression in minimising, found a matrix: "+this);
	        return false;
        }
        return true;
    }
    
    public void toMinion(StringBuilder b, boolean bool_context) {
        b.append("MINIMISING ");
        getChild(0).toMinion(b, false);
        b.append("\n");
    }
    public String toString() {
        return "minimising "+getChild(0)+"\n";
    }
    public void toDominionInner(StringBuilder b, boolean bool_context) {
        b.append("minimising ");
        getChild(0).toDominion(b, false);
        b.append("\n");
    }
    public void toFlatzinc(StringBuilder b, boolean bool_context) {
        if(!getChild(0).isConstant()) {
            b.append("minimize ");
            getChild(0).toFlatzinc(b, false);
            b.append(";\n");
        }
        else {
            b.append("satisfy;\n");
        }
    }
    public void toMinizinc(StringBuilder b, boolean bool_context) {
        b.append("minimize ");
        getChild(0).toMinizinc(b, false);
        b.append(";\n");
    }
}
