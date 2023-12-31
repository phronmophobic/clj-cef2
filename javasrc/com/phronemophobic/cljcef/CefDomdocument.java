// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefDomdocument extends Structure{
public CefDomdocument(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface GetTypeFunc extends Callback {

int get_type(CefDomdocument x0); 
}

public static interface GetDocumentFunc extends Callback {

CefDomnode get_document(CefDomdocument x0); 
}

public static interface GetBodyFunc extends Callback {

CefDomnode get_body(CefDomdocument x0); 
}

public static interface GetHeadFunc extends Callback {

CefDomnode get_head(CefDomdocument x0); 
}

public static interface GetTitleFunc extends Callback {

CefStringUtf16 get_title(CefDomdocument x0); 
}

public static interface GetElementByIdFunc extends Callback {

CefDomnode get_element_by_id(CefDomdocument x0, CefStringUtf16 x1); 
}

public static interface GetFocusedNodeFunc extends Callback {

CefDomnode get_focused_node(CefDomdocument x0); 
}

public static interface HasSelectionFunc extends Callback {

int has_selection(CefDomdocument x0); 
}

public static interface GetSelectionStartOffsetFunc extends Callback {

int get_selection_start_offset(CefDomdocument x0); 
}

public static interface GetSelectionEndOffsetFunc extends Callback {

int get_selection_end_offset(CefDomdocument x0); 
}

public static interface GetSelectionAsMarkupFunc extends Callback {

CefStringUtf16 get_selection_as_markup(CefDomdocument x0); 
}

public static interface GetSelectionAsTextFunc extends Callback {

CefStringUtf16 get_selection_as_text(CefDomdocument x0); 
}

public static interface GetBaseUrlFunc extends Callback {

CefStringUtf16 get_base_url(CefDomdocument x0); 
}

public static interface GetCompleteUrlFunc extends Callback {

CefStringUtf16 get_complete_url(CefDomdocument x0, CefStringUtf16 x1); 
}

public CefBaseRefCounted base;

public GetTypeFunc get_type;

public GetDocumentFunc get_document;

public GetBodyFunc get_body;

public GetHeadFunc get_head;

public GetTitleFunc get_title;

public GetElementByIdFunc get_element_by_id;

public GetFocusedNodeFunc get_focused_node;

public HasSelectionFunc has_selection;

public GetSelectionStartOffsetFunc get_selection_start_offset;

public GetSelectionEndOffsetFunc get_selection_end_offset;

public GetSelectionAsMarkupFunc get_selection_as_markup;

public GetSelectionAsTextFunc get_selection_as_text;

public GetBaseUrlFunc get_base_url;

public GetCompleteUrlFunc get_complete_url;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_type", "get_document", "get_body", "get_head", "get_title", "get_element_by_id", "get_focused_node", "has_selection", "get_selection_start_offset", "get_selection_end_offset", "get_selection_as_markup", "get_selection_as_text", "get_base_url", "get_complete_url");
 }

public int getType () {

return  this.get_type.get_type(this);

}

public CefDomnode getDocument () {

return  this.get_document.get_document(this);

}

public CefDomnode getBody () {

return  this.get_body.get_body(this);

}

public CefDomnode getHead () {

return  this.get_head.get_head(this);

}

public CefStringUtf16 getTitle () {

return  this.get_title.get_title(this);

}

public CefDomnode getElementById (String x1) {

return  this.get_element_by_id.get_element_by_id(this, CljCefLib.toCefString(x1));

}

public CefDomnode getFocusedNode () {

return  this.get_focused_node.get_focused_node(this);

}

public int hasSelection () {

return  this.has_selection.has_selection(this);

}

public int getSelectionStartOffset () {

return  this.get_selection_start_offset.get_selection_start_offset(this);

}

public int getSelectionEndOffset () {

return  this.get_selection_end_offset.get_selection_end_offset(this);

}

public CefStringUtf16 getSelectionAsMarkup () {

return  this.get_selection_as_markup.get_selection_as_markup(this);

}

public CefStringUtf16 getSelectionAsText () {

return  this.get_selection_as_text.get_selection_as_text(this);

}

public CefStringUtf16 getBaseUrl () {

return  this.get_base_url.get_base_url(this);

}

public CefStringUtf16 getCompleteUrl (String x1) {

return  this.get_complete_url.get_complete_url(this, CljCefLib.toCefString(x1));

}}