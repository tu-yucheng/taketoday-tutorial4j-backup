import {Routes} from '@angular/router';

import {
	auditsRoute,
	configurationRoute,
	docsRoute,
	gatewayRoute,
	healthRoute,
	logsRoute,
	metricsRoute,
	userMgmtRoute
} from './';

import {UserRouteAccessService} from 'app/core';

const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, gatewayRoute, ...userMgmtRoute, metricsRoute];

export const adminState: Routes = [
	{
		path: '',
		data: {
			authorities: ['ROLE_ADMIN']
		},
		canActivate: [UserRouteAccessService],
		children: ADMIN_ROUTES
	}
];
