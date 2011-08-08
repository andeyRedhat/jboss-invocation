/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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
package org.jboss.invocation.proxy.test.abstractsubclassfactory;

import org.jboss.invocation.proxy.AbstractSubclassFactory;
import org.jboss.invocation.proxy.DefaultReflectionMetadataSource;

import java.security.ProtectionDomain;

public class SimpleClassFactory<T> extends AbstractSubclassFactory<T> {

    public SimpleClassFactory(String className, Class<T> superClass, ClassLoader classLoader, ProtectionDomain protectionDomain) {
        super(className, superClass, classLoader, protectionDomain, DefaultReflectionMetadataSource.INSTANCE);
    }

    public SimpleClassFactory(String className, Class<T> superClass, ClassLoader classLoader) {
        this(className, superClass, classLoader, null);
    }

    public SimpleClassFactory(String className, Class<T> superClass) {
        this(className, superClass, superClass.getClassLoader());
    }

    // simply overrides public methods and constructors using the default method builder
    @Override
    protected void generateClass() {
        overridePublicMethods();
        overrideEquals();
        overrideFinalize();
        overrideHashcode();
        overrideToString();
        createConstructorDelegates();
    }

}
