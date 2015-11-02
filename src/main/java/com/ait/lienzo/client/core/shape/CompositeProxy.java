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

package com.ait.lienzo.client.core.shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.config.LienzoCore;
import com.ait.lienzo.client.core.shape.json.IFactory;
import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.shape.wires.IControlHandle.ControlHandleType;
import com.ait.lienzo.client.core.shape.wires.IControlHandleFactory;
import com.ait.lienzo.client.core.shape.wires.IControlHandleList;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.client.core.types.DragBounds;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.widget.DefaultDragConstraintEnforcer;
import com.ait.lienzo.client.widget.DragConstraintEnforcer;
import com.ait.lienzo.shared.core.types.DragConstraint;
import com.ait.lienzo.shared.core.types.DragMode;
import com.ait.lienzo.shared.core.types.NodeType;
import com.ait.lienzo.shared.core.types.ProxyType;
import com.google.gwt.json.client.JSONObject;

public abstract class CompositeProxy<C extends CompositeProxy<C, P>, P extends IPrimitive<?>> extends Node<C>implements IPrimitive<C>
{
    private ProxyType              m_type                   = null;

    private IControlHandleFactory  m_controlHandleFactory   = null;

    private DragConstraintEnforcer m_dragConstraintEnforcer = null;

    protected CompositeProxy(final ProxyType type)
    {
        super(NodeType.PROXY);

        m_type = type;
    }

    protected CompositeProxy(final ProxyType type, final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(NodeType.PROXY, node, ctx);

        m_type = type;
    }

    protected abstract P getProxy();

    protected void setProxyType(final ProxyType type)
    {
        m_type = type;
    }

    public ProxyType getProxyType()
    {
        return m_type;
    }

    @Override
    public IFactory<?> getFactory()
    {
        return LienzoCore.get().getFactory(m_type);
    }

    /**
     * Gets the X coordinate for this group.
     * 
     * @return double
     */
    @Override
    public double getX()
    {
        return getAttributes().getX();
    }

    /**
     * Sets the X coordinate for this group.
     * 
     * @param x
     * @return Group this Group
     */
    @Override
    public C setX(final double x)
    {
        getAttributes().setX(x);

        return cast();
    }

    /**
     * Gets the Y coordinate for this group.
     * 
     * @return double
     */
    @Override
    public double getY()
    {
        return getAttributes().getY();
    }

    /**
     * Sets the Y coordinate for this group.
     * 
     * @return Group this Group
     */
    @Override
    public C setY(final double y)
    {
        getAttributes().setY(y);

        return cast();
    }

    /**
     * Sets the X and Y attributes to P.x and P.y
     * 
     * @param p Point2D
     * @return Group this Group
     */
    @Override
    public C setLocation(final Point2D p)
    {
        setX(p.getX());

        setY(p.getY());

        return cast();
    }

    /**
     * Returns the X and Y attributes as a Point2D
     * 
     * @return Point2D
     */
    public Point2D getLocation()
    {
        return new Point2D(getX(), getY());
    }

    /**
     * Gets the alpha value (transparency) for this group.
     * 
     * @return double between 0 and 1
     */
    @Override
    public double getAlpha()
    {
        return getAttributes().getAlpha();
    }

    /**
     * Sets the alpha value (transparency) on this group.
     * 
     * @param alpha between 0 and 1
     * @return Group this Group
     */
    @Override
    public C setAlpha(final double alpha)
    {
        getAttributes().setAlpha(alpha);

        return cast();
    }

    /**
     * Gets the alpha value (transparency) for this group.
     * 
     * @return double between 0 and 1
     */
    @Override
    public double getFillAlpha()
    {
        return getAttributes().getFillAlpha();
    }

    /**
     * Sets the alpha value (transparency) on this group.
     * 
     * @param alpha between 0 and 1
     * @return Group this Group
     */
    @Override
    public C setFillAlpha(final double alpha)
    {
        getAttributes().setFillAlpha(alpha);

        return cast();
    }

    /**
     * Sets the alpha color on this shape.
     * 
     * @param alpha
     * @return T
     */
    @Override
    public C setStrokeAlpha(final double alpha)
    {
        getAttributes().setStrokeAlpha(alpha);

        return cast();
    }

    /**
     * Gets the alpha value for this shape.
     * 
     * @return double
     */
    @Override
    public double getStrokeAlpha()
    {
        return getAttributes().getStrokeAlpha();
    }

    /**
     * Returns whether this group can be dragged.
     * 
     * @return boolean 
     */
    @Override
    public boolean isDraggable()
    {
        return getAttributes().isDraggable();
    }

    /**
     * Sets if this group can be dragged.
     * 
     * @param draggable true if the group can be dragged; false otherwise
     * @return Group this Group
     */
    @Override
    public C setDraggable(final boolean draggable)
    {
        getAttributes().setDraggable(draggable);

        return cast();
    }

    @Override
    public boolean isEditable()
    {
        return getAttributes().isEditable();
    }

    @Override
    public C setEditable(final boolean editable)
    {
        getAttributes().setEditable(editable);

        return cast();
    }

    /**
     * Gets the group's scale.
     * 
     * @return {@link Point2D}
     */
    @Override
    public Point2D getScale()
    {
        return getAttributes().getScale();
    }

    /**
     * Sets the group's scale, starting at the given point.
     * 
     * @param scale
     * @return Group this Group
     */
    @Override
    public C setScale(final Point2D scale)
    {
        getAttributes().setScale(scale);

        return cast();
    }

    /**
     * Sets this group's scale, with the same value for x and y.
     * 
     * @param xy
     * @return Group this Group
     */
    @Override
    public C setScale(final double xy)
    {
        getAttributes().setScale(xy);

        return cast();
    }

    /**
     * Sets this gruop's scale, starting at the given x and y
     * 
     * @param x
     * @param y
     * @return Group this Group
     */
    @Override
    public C setScale(final double x, final double y)
    {
        getAttributes().setScale(x, y);

        return cast();
    }

    /**
     * Gets this group's rotation, in radians.
     * 
     * @return double
     */
    @Override
    public double getRotation()
    {
        return getAttributes().getRotation();
    }

    /**
     * Sets this group's rotation, in radians.
     * 
     * @param radians
     * @return Group this Group
     */
    @Override
    public C setRotation(final double radians)
    {
        getAttributes().setRotation(radians);

        return cast();
    }

    /**
     * Gets this group's rotation, in degrees.
     * 
     * @return double
     */
    @Override
    public double getRotationDegrees()
    {
        return getAttributes().getRotationDegrees();
    }

    /**
     * Sets this group's rotation, in degrees.
     * 
     * @param degrees
     * @return Group this Group
     */
    @Override
    public C setRotationDegrees(final double degrees)
    {
        getAttributes().setRotationDegrees(degrees);

        return cast();
    }

    /**
     * Gets this group's offset as a {@link Point2D}
     * 
     * @return Point2D
     */
    @Override
    public Point2D getOffset()
    {
        return getAttributes().getOffset();
    }

    /**
     * Gets this group's shear as a {@link Point2D}
     * 
     * @return Point2D
     */
    @Override
    public Point2D getShear()
    {
        return getAttributes().getShear();
    }

    /**
     * Sets this group's shear
     * 
     * @param offset
     * @return T
     */
    @Override
    public C setShear(final Point2D shear)
    {
        getAttributes().setShear(shear);

        return cast();
    }

    /**
     * Sets this group's shear
     * 
     * @param offset
     * @return T
     */
    @Override
    public C setShear(final double x, final double y)
    {
        getAttributes().setShear(x, y);

        return cast();
    }

    /**
     * Sets this group's offset
     * 
     * @param offset
     * @return Group this Group
     */
    @Override
    public C setOffset(final Point2D offset)
    {
        getAttributes().setOffset(offset);

        return cast();
    }

    /**
     * Sets this group's offset, with the same value for x and y.
     * 
     * @param xy
     * @return Group this Group
     */
    @Override
    public C setOffset(final double xy)
    {
        getAttributes().setOffset(xy);

        return cast();
    }

    /**
     * Sets this group's offset, at the given x and y coordinates.
     * 
     * @param x
     * @param y
     * @return Group this Group
     */
    @Override
    public C setOffset(final double x, final double y)
    {
        getAttributes().setOffset(x, y);

        return cast();
    }

    /**
     * Gets this group's {@link DragConstraint}
     * 
     * @return DragConstraint
     */
    @Override
    public DragConstraint getDragConstraint()
    {
        return getAttributes().getDragConstraint();
    }

    /**
     * Sets this group's drag constraint, 
     * e.g. horizontal, vertical or none (default)
     * 
     * @param constraint
     * @return Group this Group
     */
    @Override
    public C setDragConstraint(final DragConstraint constraint)
    {
        getAttributes().setDragConstraint(constraint);

        return cast();
    }

    /**
     * Gets the {@link DragBounds} for this group.
     * 
     * @return DragBounds
     */
    @Override
    public DragBounds getDragBounds()
    {
        return getAttributes().getDragBounds();
    }

    /**
     * Sets this group's drag bounds.
     * 
     * @param bounds
     * @return Group this Group
     */
    @Override
    public C setDragBounds(final DragBounds bounds)
    {
        getAttributes().setDragBounds(bounds);

        return cast();
    }

    /**
     * Gets the {@link DragMode} for this node.
     * 
     * @return DragMode
     */
    @Override
    public DragMode getDragMode()
    {
        return getAttributes().getDragMode();
    }

    /**
     * Sets this node's drag mode.
     * 
     * @param mode
     * @return Group this Group
     */
    @Override
    public C setDragMode(final DragMode mode)
    {
        getAttributes().setDragMode(mode);

        return cast();
    }

    @Override
    public boolean removeFromParent()
    {
        Node<?> parent = getParent();

        if (null != parent)
        {
            Layer layer = parent.asLayer();

            if (null != layer)
            {
                layer.remove(this);

                return true;
            }
            GroupOf<IPrimitive<?>, ?> group = parent.asGroupOf();

            if (null != group)
            {
                group.remove(this);

                return true;
            }
        }
        return false;
    }

    /**
    * Moves this shape one layer up.
    * 
    * @return T
    */
    @SuppressWarnings("unchecked")
    @Override
    public C moveUp()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveUp(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape one layer down.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public C moveDown()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveDown(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape to the top of the layers stack.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public C moveToTop()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveToTop(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape to the bottomw of the layers stack.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public C moveToBottom()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveToBottom(this);
            }
        }
        return cast();
    }

    @Override
    public Map<ControlHandleType, IControlHandleList> getControlHandles(ControlHandleType... types)
    {
        return getControlHandles(Arrays.asList(types));
    }

    @Override
    public Map<ControlHandleType, IControlHandleList> getControlHandles(List<ControlHandleType> types)
    {
        if ((null == types) || (types.isEmpty()))
        {
            return null;
        }
        if (types.size() > 1)
        {
            types = new ArrayList<ControlHandleType>(new HashSet<ControlHandleType>(types));
        }
        IControlHandleFactory factory = getControlHandleFactory();

        if (null == factory)
        {
            return null;
        }
        return factory.getControlHandles(types);
    }

    @Override
    public IControlHandleFactory getControlHandleFactory()
    {
        return m_controlHandleFactory;
    }

    @Override
    public C setControlHandleFactory(IControlHandleFactory factory)
    {
        m_controlHandleFactory = factory;

        return cast();
    }

    @Override
    public JSONObject toJSONObject()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DragConstraintEnforcer getDragConstraints()
    {
        if (null == m_dragConstraintEnforcer)
        {
            return new DefaultDragConstraintEnforcer();
        }
        else
        {
            return m_dragConstraintEnforcer;
        }
    }

    @Override
    public C setDragConstraints(final DragConstraintEnforcer enforcer)
    {
        m_dragConstraintEnforcer = enforcer;

        return cast();
    }

    @Override
    public void attachToLayerColorMap()
    {
        getProxy().attachToLayerColorMap();
    }

    @Override
    public void detachFromLayerColorMap()
    {
        getProxy().detachFromLayerColorMap();
    }

    @Override
    public C refresh()
    {
        getProxy().refresh();

        return cast();
    }

    @Override
    public C copy()
    {
        return null;
    }

    @Override
    protected void drawWithoutTransforms(final Context2D context, double alpha, final BoundingBox bounds)
    {
        if ((context.isSelection()) && (false == isListening()))
        {
            return;
        }
        alpha = alpha * getAttributes().getAlpha();

        if (alpha <= 0)
        {
            return;
        }
        getProxy().drawWithTransforms(context, alpha, bounds);
    }

    protected static abstract class CompositeProxyFactory<C extends CompositeProxy<C, P>, P extends IPrimitive<?>> extends NodeFactory<C>
    {
        protected CompositeProxyFactory(final ProxyType type)
        {
            super(type.getValue());

            addAttribute(Attribute.X);

            addAttribute(Attribute.Y);

            addAttribute(Attribute.ALPHA);

            addAttribute(Attribute.FILL);

            addAttribute(Attribute.FILL_ALPHA);

            addAttribute(Attribute.STROKE);

            addAttribute(Attribute.STROKE_WIDTH);

            addAttribute(Attribute.STROKE_ALPHA);

            addAttribute(Attribute.DRAGGABLE);

            addAttribute(Attribute.EDITABLE);

            addAttribute(Attribute.SCALE);

            addAttribute(Attribute.SHEAR);

            addAttribute(Attribute.ROTATION);

            addAttribute(Attribute.OFFSET);

            addAttribute(Attribute.SHADOW);

            addAttribute(Attribute.LINE_CAP);

            addAttribute(Attribute.LINE_JOIN);

            addAttribute(Attribute.MITER_LIMIT);

            addAttribute(Attribute.DRAG_CONSTRAINT);

            addAttribute(Attribute.DRAG_BOUNDS);

            addAttribute(Attribute.DRAG_MODE);

            addAttribute(Attribute.DASH_ARRAY);

            addAttribute(Attribute.DASH_OFFSET);

            addAttribute(Attribute.EVENT_PROPAGATION_MODE);
        }

        protected void setProxyType(final ProxyType type)
        {
            setTypeName(type.getValue());
        }
    }
}
