/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.invocation.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Stuart Douglas
 */
public class DefaultClassMetadataSource implements ClassMetadataSource {

    private final Class<?> clazz;
    private final List<Method> declaredMethods;
    private final List<Method> methods;
    private final List<Constructor<?>> constructors;

    public DefaultClassMetadataSource(final Class<?> clazz) {
        this.clazz = clazz;
        this.declaredMethods = Arrays.asList(clazz.getDeclaredMethods());
        this.methods = Arrays.asList(clazz.getMethods());
        this.constructors = Arrays.asList(clazz.getConstructors());
    }


    public Collection<Method> getDeclaredMethods() {
        return declaredMethods;
    }

    @Override
    public Collection<Method> getMethods() {
        return methods;
    }

    @Override
    public Method getMethod(final String methodName, final Class<?> returnType, final Class<?>... parameters) throws NoSuchMethodException {
        return clazz.getMethod(methodName, parameters);
    }

    @Override
    public Collection<Constructor<?>> getConstructors() {
        return constructors;
    }

}
