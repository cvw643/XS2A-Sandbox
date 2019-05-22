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
    this.infoService.openFeedback('Error occured', {
      severity: 'error'
    });
    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params);
    });
  }

}
