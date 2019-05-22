import {ErrorHandler, Injectable, Injector, NgZone} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {InfoService} from "../info/info.service";

@Injectable()
export class ObaErrorsHandler implements ErrorHandler {
  constructor(
    private zone: NgZone,
    private infoService: InfoService,
    private injector: Injector) {
  }

  public get router(): Router {
    return this.injector.get(Router);
  }

  public get activatedRoute(): ActivatedRoute {
    return this.injector.get(ActivatedRoute);
  }

  public handleError(error: any) {
    console.error(error);
    let httpErrorCode = error.httpErrorCode;

    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params);
    });

    switch(httpErrorCode) {
      case 401: {
        this.infoService.openFeedback('401 Error occured', {
          severity: 'error'
        });
        break;
      }
      case 403: {
        this.infoService.openFeedback('403 Error occured', {
          severity: 'error'
        });
        break;
      }
      default: {
        this.infoService.openFeedback('500 Error occured', {
          severity: 'error'
        });
        break;
      }
    }
  }

}
