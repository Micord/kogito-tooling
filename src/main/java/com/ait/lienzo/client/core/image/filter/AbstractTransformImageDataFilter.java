/*
   Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.client.core.image.filter;

import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.types.ImageData;
import com.ait.lienzo.shared.core.types.ImageFilterType;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.json.client.JSONObject;

public abstract class AbstractTransformImageDataFilter<T extends AbstractTransformImageDataFilter<T>> extends AbstractImageDataFilter<T>
{
    protected AbstractTransformImageDataFilter(final ImageFilterType type)
    {
        super(type);
    }

    protected AbstractTransformImageDataFilter(final ImageFilterType type, final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(type, node, ctx);
    }

    @Override
    public ImageData filter(ImageData source, final boolean copy)
    {
        if (null == source)
        {
            return null;
        }
        if (copy)
        {
            source = source.copy();
        }
        if (false == isActive())
        {
            return source;
        }
        final CanvasPixelArray data = source.getData();

        if (null == data)
        {
            return source;
        }
        final FilterTransformFunction transform = getTransform();

        if (null == transform)
        {
            return source;
        }
        final ImageData result = source.create();

        FilterCommonOps.doFilterTransform(data, result.getData(), transform, source.getWidth(), source.getHeight());

        return result;
    }

    @Override
    public final boolean isTransforming()
    {
        return true;
    }

    protected abstract FilterTransformFunction getTransform();
}
