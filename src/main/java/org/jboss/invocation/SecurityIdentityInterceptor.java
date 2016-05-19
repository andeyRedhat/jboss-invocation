/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

package org.jboss.invocation;

import java.security.PrivilegedActionException;

import org.wildfly.security.auth.server.SecurityIdentity;

/**
 * An interceptor which executes under the invocation's {@link SecurityIdentity}.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class SecurityIdentityInterceptor implements Interceptor {
    private static final SecurityIdentityInterceptor INSTANCE = new SecurityIdentityInterceptor();
    private static final InterceptorFactory FACTORY = new ImmediateInterceptorFactory(INSTANCE);

    private SecurityIdentityInterceptor() {
    }

    /**
     * Get the singleton instance.
     *
     * @return the singleton instance
     */
    public static SecurityIdentityInterceptor getInstance() {
        return INSTANCE;
    }

    /**
     * Get a factory which returns the singleton instance.
     *
     * @return a factory which returns the singleton instance
     */
    public static InterceptorFactory getFactory() {
        return FACTORY;
    }

    /** {@inheritDoc} */
    public Object processInvocation(final InterceptorContext context) throws Exception {
        final SecurityIdentity identity = context.getPrivateData(SecurityIdentity.class);
        if (identity != null) try {
            return identity.runAs(context);
        } catch (PrivilegedActionException e) {
            throw e.getException();
        } else {
            return context.proceed();
        }
    }
}
